package by.lebedev.miningcalculator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.domain.entities.CoinData
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.usecase.GetCoinCapRatesUseCaseImpl
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.coinrate.CoinRateAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.coinrates_layout.*


class CoinRatesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.coinrates_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCoinRates()

        swipeRefreshCoinRates.setColorSchemeResources(
            R.color.green_24_hour_percent,
            R.color.colorPrimary,
            R.color.red_info
        )
        swipeRefreshCoinRates.setOnRefreshListener {
            layoutForRefreshCoinRates.visibility = View.INVISIBLE
            getCoinRates()
        }

    }

    private fun getCoinRates() {

        val d = GetCoinCapRatesUseCaseImpl().fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (result != null && progressCoinRates != null) {
                    layoutForRefreshCoinRates.visibility = View.VISIBLE
                    swipeRefreshCoinRates.isRefreshing = false
                    progressCoinRates.visibility = View.INVISIBLE
                    setupRecycler(result)

                }
            }, {
                Log.e(TAG, it.localizedMessage)
            })
    }

    private fun setupRecycler(coinRateList: ArrayList<CoinRate>) {
        coinRateRecycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL
        coinRateRecycle.layoutManager = layoutManager
        coinRateRecycle.adapter = this.context?.let { CoinRateAdapter(coinRateList) }
    }

}