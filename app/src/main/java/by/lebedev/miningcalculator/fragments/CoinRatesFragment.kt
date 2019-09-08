package by.lebedev.miningcalculator.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.domain.entities.CoinProfitabilityString
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.transformators.CoinProfitabilityEnergyFeeCalculator
import by.lebedev.domain.transformators.CoinProfitabilityStringTransformator
import by.lebedev.domain.usecase.GetCoinCapRatesUseCase
import by.lebedev.domain.usecase.GetProfitableCoinsUseCaseCryptonight
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.coinrate.CoinRateAdapter
import by.lebedev.miningcalculator.recyclers.earningsrecycler.EarningsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.coinrates_layout.*
import kotlinx.android.synthetic.main.devices_layout.*
import kotlinx.android.synthetic.main.earnings_layout.*


class CoinRatesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.coinrates_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCoinRates()


    }

    fun getCoinRates() {

        val d = GetCoinCapRatesUseCase().fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                if (result != null) {
                    progressCoinRates.visibility = View.INVISIBLE
                    setupRecycler(result)
                }


            }, {
                Log.e("AAA", it.message)
            })
    }

    fun setupRecycler(coinRateList: ArrayList<CoinRate>) {
        coinRateRecycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        coinRateRecycle.layoutManager = layoutManager
        coinRateRecycle.adapter = this.context?.let { CoinRateAdapter(it, coinRateList) }
    }

}