package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.lebedev.domain.entities.PoolsItem
import by.lebedev.domain.repository.CoinTempData
import by.lebedev.domain.transformators.AllPoolsToCoinSpecificTransformator
import by.lebedev.domain.usecase.GetAllPoolsUseCaseImpl
import by.lebedev.domain.usecase.GetCoinDetailsUseCaseImpl
import by.lebedev.miningcalculator.fragments.CoinHistoryChart
import by.lebedev.miningcalculator.recyclers.coinrate.COIN_TAG
import by.lebedev.miningcalculator.recyclers.poolrecycler.PoolAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.amd_layout.*
import kotlinx.android.synthetic.main.rate_details_layout.*
import java.text.NumberFormat

class RatesDetailActivity : AppCompatActivity(), View.OnClickListener {
    private val nf = NumberFormat.getInstance()
    private val nf0 = NumberFormat.getInstance()
    private var coinSpecPools = ArrayList<PoolsItem>()
    lateinit var mAdView: AdView

    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rate_details_layout)

        mAdView = adViewCoinRate
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        M1.setOnClickListener(this)
        M5.setOnClickListener(this)
        M15.setOnClickListener(this)
        M30.setOnClickListener(this)
        H1.setOnClickListener(this)
        H6.setOnClickListener(this)
        H12.setOnClickListener(this)
        D1.setOnClickListener(this)
        sortName.setOnClickListener(this)
        sortFee.setOnClickListener(this)
        sortRating.setOnClickListener(this)
        contactUsTextView.setOnClickListener(this)

        val coinName = intent.getStringExtra(COIN_TAG)
        val coinTicker = CoinTempData.instance.coinTicker
        CoinTempData.instance.coinChartName = coinName
        setupCoinDetails(coinName)

        coinChartTextView.setOnClickListener {
            coinChartTextView.setCompoundDrawables(
                getDrawable(R.drawable.ic_arrow_left_white_24dp),
                null,
                null,
                null
            )
            onBackPressed()
        }

        inflateChart()

        setupPoolRecycler(getCoinSpecPools(coinTicker))
    }

    private fun setupPoolRecycler(poolsList: ArrayList<PoolsItem>) {

        if (poolsList.isEmpty()) {
            pools_recycler.visibility = View.GONE
            miningPoolsTextView.visibility = View.GONE
            layoutForSortingPools.visibility = View.GONE
        } else {
            pools_recycler.layoutManager = LinearLayoutManager(this)
            pools_recycler.setHasFixedSize(true)
            pools_recycler.adapter = PoolAdapter(poolsList)
        }
    }

    private fun inflateChart() {
        val supportFragmentManager = supportFragmentManager
        val historyChartFragment = CoinHistoryChart()
        supportFragmentManager.beginTransaction().replace(R.id.coinLineChart, historyChartFragment)
            .commit()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.M1 -> {
                clearAllTimeFrameBackground()
                M1.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "m1"
                inflateChart()
            }
            R.id.M5 -> {
                clearAllTimeFrameBackground()
                M5.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "m5"
                inflateChart()
            }
            R.id.M15 -> {
                clearAllTimeFrameBackground()
                M15.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "m15"
                inflateChart()
            }
            R.id.M30 -> {
                clearAllTimeFrameBackground()
                M30.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "m30"
                inflateChart()
            }
            R.id.H1 -> {
                clearAllTimeFrameBackground()
                H1.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "h1"
                inflateChart()
            }
            R.id.H6 -> {
                clearAllTimeFrameBackground()
                H6.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "h6"
                inflateChart()
            }
            R.id.H12 -> {
                clearAllTimeFrameBackground()
                H12.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "h12"
                inflateChart()
            }
            R.id.D1 -> {
                clearAllTimeFrameBackground()
                D1.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                CoinTempData.instance.coinTimeFrame = "d1"
                inflateChart()
            }
            R.id.sortName -> {
                clearAllFiltersBackground()
                sortName.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                coinSpecPools.sortBy {
                    it.name
                }
                setupPoolRecycler(coinSpecPools)
            }
            R.id.sortFee -> {
                clearAllFiltersBackground()
                sortFee.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                coinSpecPools.sortBy {
                    it.averageFee
                }
                setupPoolRecycler(coinSpecPools)
            }
            R.id.sortRating -> {
                clearAllFiltersBackground()
                sortRating.background =
                    ContextCompat.getDrawable(this, R.drawable.rounded_worker_details_black_frame)
                coinSpecPools.sortByDescending {
                    it.rating.avg
                }
                setupPoolRecycler(coinSpecPools)
            }
            R.id.contactUsTextView -> {
                val email = Intent(Intent.ACTION_SEND)
                email.type = getString(R.string.text_email)
                email.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.my_email)))
                email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_mining_app))
                email.putExtra(Intent.EXTRA_TEXT, getString(R.string.dear_me) + "\n")
                startActivity(Intent.createChooser(email, getString(R.string.send_feedback)))
            }
        }
    }

    private fun clearAllTimeFrameBackground() {
        M1.background = null
        M5.background = null
        M15.background = null
        M30.background = null
        H1.background = null
        H6.background = null
        H12.background = null
        D1.background = null
    }

    private fun clearAllFiltersBackground() {
        sortName.background = null
        sortFee.background = null
        sortRating.background = null
    }

    private fun setupCoinDetails(coinName: String) {
        nf.maximumFractionDigits = 2
        nf0.maximumFractionDigits = 0

        val disposable = GetCoinDetailsUseCaseImpl().fetch(coinName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                coinChartTextView.text = it.name.plus(" Chart")
                Picasso.get()
                    .load("https://static.coincap.io/assets/icons/${it.symbol.toLowerCase()}@2x.png")
                    .error(R.drawable.nocoinlogo)
                    .into(coinImage)
                name.text = getString(R.string.name_chart).plus("\n").plus(it.name)
                symbol.text = getString(R.string.symbol_chart).plus("\n").plus(it.symbol)
                price.text =
                    getString(R.string.price_chart).plus("\n").plus(nf.format(it.priceUsd))
                        .plus(" ").plus("$")
                coinPriceTop.text = nf0.format(it.priceUsd).plus(" $")
                weightedPrice.text = getString(R.string.daily_average_price_chart).plus("\n")
                    .plus(nf.format(it.vwap24Hr))
                supply.text = getString(R.string.supply_chart).plus("\n").plus(nf.format(it.supply))
                maxSupply.text =
                    getString(R.string.max_supply_chart).plus("\n").plus(nf.format(it.maxSupply))
                marketCapUsd.text =
                    getString(R.string.market_cap_chart).plus("\n").plus(nf.format(it.marketCapUsd))
                volume24Hours.text =
                    getString(R.string.volume_in_24h_chart).plus("\n")
                        .plus(nf.format(it.volumeUsd24Hr))
                percentChange24H.text = if (it.changePercent24Hr > 0) {
                    "+${nf.format(it.changePercent24Hr)}%"
                } else {
                    "${nf.format(it.changePercent24Hr)}%"
                }
                if (it.changePercent24Hr > 0) {
                    percentChange24H.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.green_percent_change
                        )
                    )
                } else {
                    percentChange24H.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.red_percent_change
                        )
                    )
                }


            }, {
                Log.e(by.lebedev.miningcalculator.fragments.TAG, it.localizedMessage)
            })
        compositeDisposable.add(disposable)
    }

    private fun getCoinSpecPools(coinTicker: String): ArrayList<PoolsItem> {
        val pools = GetAllPoolsUseCaseImpl().fetch(this)
        val coinSpecPools = AllPoolsToCoinSpecificTransformator().execute(coinTicker, pools)
        coinSpecPools.sortBy {
            it.name
        }
        this.coinSpecPools = coinSpecPools
        return coinSpecPools
    }
}