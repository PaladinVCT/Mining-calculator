package by.lebedev.miningcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import by.lebedev.domain.CoinProfitabilityResponseTransformator
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.domain.usecase.GetProfitableCoinsUseCaseCryptonight
import by.lebedev.miningcalculator.earningsrecycler.EarningsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.earnings_layout.*

class EarningsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earnings_layout)

        val selectedItem = intent.getIntExtra("selectedItem", -1)
        val hashrate = intent.getLongExtra("hashrate", -1)
        val device = intent.getStringExtra("device")

        getEarnings(selectedItem, hashrate, device)

    }

    fun getEarnings(selectedItem: Int, hashrate: Long, device: String) {

        val d = GetProfitableCoinsUseCaseCryptonight().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("AAA", result.get(0).toString())

                setupRecycler(result)

            }, {
                Log.e("AAA", it.message)
            })


    }

    fun setupRecycler(coinList: ArrayList<CoinProfitability>) {
        earnings_recycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        earnings_recycle.layoutManager = layoutManager
        earnings_recycle.adapter = EarningsAdapter(coinList)
    }


}