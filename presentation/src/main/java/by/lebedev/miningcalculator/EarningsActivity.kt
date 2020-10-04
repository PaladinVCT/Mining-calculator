package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.entities.CoinProfitabilityString
import by.lebedev.domain.repository.CoinTempData
import by.lebedev.domain.transformators.*
import by.lebedev.domain.usecase.*
import by.lebedev.miningcalculator.fragments.*
import by.lebedev.miningcalculator.recyclers.earningsrecycler.EarningsAdapter
import com.google.android.gms.ads.AdRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.earnings_layout.*

private const val GPU = "GPU"
private const val CPU = "CPU"
private const val ASIC = "ASIC"
private const val RIG = "RIG"
private const val RIGAMD = "RIGAMD"

class EarningsActivity : BaseEarningsActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earnings_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        loadAllCoinsIntoTempData()

        val selectedItem = intent.getIntExtra(SELECTED_ITEM, -1)

        val device = intent.getStringExtra(DEVICE)

        val hashrate =
            intent.getDoubleExtra(HASHRATE, -1.0) * getHashrateMultiplier(device, selectedItem)
        Log.e(TAG, hashrate.toString())

        val energy = intent.getDoubleExtra(ENERGY, 0.0)
        val energyCost = intent.getDoubleExtra(ENERGY_COST, 0.0)
        val fee = intent.getDoubleExtra(FEE, 0.0)

        getEarnings(selectedItem, hashrate, device, energy, energyCost, fee)

        swipeRefreshEarnings.setColorSchemeResources(
            R.color.green_24_hour_percent,
            R.color.colorPrimary,
            R.color.red_info
        )
        swipeRefreshEarnings.setOnRefreshListener {
            layoutForRefreshEarnings.visibility = View.INVISIBLE
            getEarnings(selectedItem, hashrate, device, energy, energyCost, fee)
        }

    }

    private fun getEarningsCryptonight(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseCryptonightImpl().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                processEarningsData(energy, energyCost, fee, result)

            }, {
                textForError.visibility = View.VISIBLE
                earningsProgressBar.visibility = View.INVISIBLE
                Log.e(TAG, it.localizedMessage)
            })

        compositeDisposable.add(d)

    }

    private fun getEarningsNvidia(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseNvidiaImpl().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                processEarningsData(energy, energyCost, fee, result)

            }, {
                textForError.visibility = View.VISIBLE
                earningsProgressBar.visibility = View.INVISIBLE
                Log.e(TAG, it.localizedMessage)
            })

        compositeDisposable.add(d)
    }

    private fun getEarningsAsic(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseAsicImpl().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                processEarningsData(energy, energyCost, fee, result)

            }, {
                textForError.visibility = View.VISIBLE
                earningsProgressBar.visibility = View.INVISIBLE
                Log.e(TAG, it.localizedMessage)
            })

        compositeDisposable.add(d)

    }

    private fun getEarningsNvidiaRig(
        selectedItem: Int,
        hashrate: Double,
        device: String
    ) {

        val d = GetAllProfitableCoinsUseCaseNvidiaImpl().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                if (result != null && earnings_recycle != null && earningsProgressBar != null) {

                    earningsProgressBar.visibility = View.INVISIBLE

                    val profit =
                        CoinProfitabilityNvidiaRigTransformator().execute(result, hashrate)

                    val profitArrayString = CoinProfitabilityStringTransformator().execute(profit)
                    setupRecycler(profitArrayString)
                }

            }, {
                Log.e(TAG, it.localizedMessage)
            })
        compositeDisposable.add(d)

    }

    private fun getEarningsAMDRig(
        selectedItem: Int,
        hashrate: Double,
        device: String
    ) {

        val summaryProfitableCoins = ArrayList<CoinProfitability>()

        val d = GetAllProfitableCoinsUseCaseNvidiaImpl().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                if (result != null && earnings_recycle != null && earningsProgressBar != null) {

                    val profitNvidiaAlgos =
                        CoinProfitabilityAMDRigTransformator().execute(result, hashrate)


                    val x = GetProfitableCoinsUseCaseCryptonightImpl().fetch(
                        selectedItem, HashPowerAggregator().execute(
                            AmdDevices.instance.list
                        ), device
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ profitAMDAlgos ->
                            earningsProgressBar.visibility = View.INVISIBLE

                            summaryProfitableCoins.addAll(profitAMDAlgos)
                            summaryProfitableCoins.addAll(profitNvidiaAlgos)

                            summaryProfitableCoins.sortByDescending {
                                it.rewardDayUsdActual
                            }

                            val profitArrayString = CoinProfitabilityStringTransformator().execute(
                                summaryProfitableCoins
                            )
                            setupRecycler(profitArrayString)
                        }, {
                            textForError.visibility = View.VISIBLE
                            earningsProgressBar.visibility = View.INVISIBLE
                            Log.e(TAG, it.localizedMessage)
                        })
                    compositeDisposable.add(x)
                }

            }, {
                Log.e(TAG, it.localizedMessage)
            })
        compositeDisposable.add(d)
    }

    private fun setupRecycler(coinList: ArrayList<CoinProfitabilityString>) {
        layoutForRefreshEarnings.visibility = View.VISIBLE
        swipeRefreshEarnings.isRefreshing = false
        earnings_recycle.setHasFixedSize(true)
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        earnings_recycle.layoutManager = layoutManager
        earnings_recycle.adapter = EarningsAdapter(coinList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dot_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.support -> {
                mInterstitialAd.loadAd(AdRequest.Builder().build())

                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()

                } else {
                    Log.e(TAG, getString(R.string.interstitial_not_loaded_yet))
                }

                val intent = Intent(this, WarningActivity::class.java)
                startActivity(intent)

                return true
            }
            R.id.feedback -> {

                val Email = Intent(Intent.ACTION_SEND)
                Email.type = getString(R.string.text_email)
                Email.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.my_email)))
                Email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_mining_app))
                Email.putExtra(Intent.EXTRA_TEXT, getString(R.string.dear_me) + "\n")
                startActivity(Intent.createChooser(Email, getString(R.string.send_feedback)))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getHashrateMultiplier(device: String, selectedItem: Int): Long {
        return if (device == GPU || device == CPU) {
            HashTypeConfigurator().getDigitsFromType(
                HashTypeConfigurator().getTypeFromName(
                    Algos.instance.gpuList[selectedItem]
                )
            )
        } else {
            HashTypeConfigurator().getDigitsFromType(
                HashTypeConfigurator().getTypeFromName(
                    Algos.instance.asicList[selectedItem]
                )
            )
        }
    }

    private fun processEarningsData(
        energy: Double,
        energyCost: Double,
        fee: Double,
        result: ArrayList<CoinProfitability>?
    ) {
        if (result != null && earnings_recycle != null && earningsProgressBar != null) {

            earningsProgressBar.visibility = View.INVISIBLE
            val profitMinusFee = CoinProfitabilityEnergyFeeCalculator()
                .execute(result, energy, energyCost, fee)
            val profitArrayString = CoinProfitabilityStringTransformator().execute(profitMinusFee)
            setupRecycler(profitArrayString)
        }

    }

    private fun getEarnings(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        if (selectedItem == 0 && device != ASIC) {
            getEarningsCryptonight(selectedItem, hashrate, device, energy, energyCost, fee)
        } else if (device == RIG) {
            Log.e(TAG, RIG)
            getEarningsNvidiaRig(selectedItem, hashrate, device)
        } else if (device == GPU) {
            getEarningsNvidia(selectedItem, hashrate, device, energy, energyCost, fee)
        } else if (device == ASIC) {
            Log.e(TAG, getString(R.string.getting_asic_profitability))
            getEarningsAsic(selectedItem, hashrate, device, energy, energyCost, fee)
        } else if (device == RIGAMD) {
            Log.e(TAG, getString(R.string.getting_rig_amd_prifitability))
            getEarningsAMDRig(selectedItem, hashrate, device)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }


    private fun loadAllCoinsIntoTempData() {

        val disposable = GetCoinGeckoCoinsUseCaseImpl().fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result?.let { CoinTempData.instance.allGeckoCoinList = result }
            }, {
                Log.e(TAG, it.localizedMessage)
            })
        compositeDisposable.add(disposable)
    }
}