package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.transformators.CoinProfitabilityEnergyFeeCalculator
import by.lebedev.domain.transformators.CoinProfitabilityStringTransformator
import by.lebedev.domain.entities.CoinProfitabilityString
import by.lebedev.domain.transformators.CoinProfitabilityRigTransformator
import by.lebedev.domain.transformators.HashTypeConfigurator
import by.lebedev.domain.usecase.GetAllProfitableCoinsUseCaseNvidia
import by.lebedev.domain.usecase.GetProfitableCoinsUseCaseCryptonight
import by.lebedev.domain.usecase.GetProfitableCoinsUseCaseNvidia
import by.lebedev.miningcalculator.recyclers.earningsrecycler.EarningsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.earnings_layout.*

class EarningsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earnings_layout)

        val selectedItem = intent.getIntExtra("selectedItem", -1)

        val hashrate = intent.getDoubleExtra("hashrate", -1.0) * HashTypeConfigurator().getDigitsFromType(
            HashTypeConfigurator().getTypeFromName(
                Algos.instance.list.get(selectedItem)
            )
        )
        Log.e("AAA", hashrate.toString())

        val device = intent.getStringExtra("device")
        val energy = intent.getDoubleExtra("energy", 0.0)
        val energyCost = intent.getDoubleExtra("energyCost", 0.0)
        val fee = intent.getDoubleExtra("fee", 0.0)

        if (selectedItem == 0) {
            getEarningsCryptonight(selectedItem, hashrate, device, energy, energyCost, fee)
        } else if (device.equals("RIG")) {
            Log.e("AAA", "RIG")
            getEarningsNvidiaRig(selectedItem, hashrate, device, energy, energyCost, fee)
        } else {
            getEarningsNvidia(selectedItem, hashrate, device, energy, energyCost, fee)
        }
    }

    fun getEarningsCryptonight(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseCryptonight().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("AAA", result.get(0).toString())

                earningsProgressBar.visibility = View.INVISIBLE
                val profit = result
                val profitMinusFee = CoinProfitabilityEnergyFeeCalculator()
                    .execute(profit, energy, energyCost, fee)
                val profitArrayString = CoinProfitabilityStringTransformator().execute(profitMinusFee)
                setupRecycler(profitArrayString)


            }, {
                Log.e("AAA", it.message)
            })


    }

    fun getEarningsNvidia(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseNvidia().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("AAA", result.get(0).toString())

                earningsProgressBar.visibility = View.INVISIBLE
                val profit = result
                val profitMinusFee = CoinProfitabilityEnergyFeeCalculator()
                    .execute(profit, energy, energyCost, fee)
                val profitArrayString = CoinProfitabilityStringTransformator().execute(profitMinusFee)
                setupRecycler(profitArrayString)


            }, {
                Log.e("AAA", it.message)
            })


    }

    fun getEarningsNvidiaRig(
        selectedItem: Int,
        hashrate: Double,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetAllProfitableCoinsUseCaseNvidia().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("AAA", result.get(0).coinName)
                Log.e("AAA", result.get(1).coinName)
                Log.e("AAA", result.get(2).coinName)
                Log.e("AAA", result.get(3).coinName)
                earningsProgressBar.visibility = View.INVISIBLE

                val globalProfit = result

                val profit = CoinProfitabilityRigTransformator().execute(globalProfit, hashrate)

                val profitArrayString = CoinProfitabilityStringTransformator().execute(profit)
                setupRecycler(profitArrayString)


            }, {
                Log.e("AAA", it.message)
            })


    }


    fun setupRecycler(coinList: ArrayList<CoinProfitabilityString>) {
        earnings_recycle.setHasFixedSize(true)
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        earnings_recycle.layoutManager = layoutManager
        earnings_recycle.adapter = EarningsAdapter(coinList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.dot_menu, menu)
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

//                val intent = Intent(this, AboutActivity::class.java)
//                startActivity(intent)
                return true
            }
            R.id.feedback -> {

                val Email = Intent(Intent.ACTION_SEND)
                Email.type = "text/email"
                Email.putExtra(Intent.EXTRA_EMAIL, arrayOf("developer.alexandr.lebedev@gmail.com"))
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback Mining Calculator App")
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Alexandr Lebedev," + "\n")
                startActivity(Intent.createChooser(Email, "Send Feedback:"))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}