package by.lebedev.miningcalculator.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.usecase.GetCoinCapRatesUseCaseImpl
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.recyclers.coinrate.CoinRateAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.coinrates_layout.*


class CoinRatesFragment : Fragment() {

    private val startCoinList = ArrayList<CoinRate>()
    private val searchedCoinList = ArrayList<CoinRate>()

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

        searchCoin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(enteredText: Editable?) {
                searchedCoinList.clear()

                if (enteredText.toString().isEmpty()) {
                    setupRecycler(startCoinList)
                } else {

                    for (i in 0 until startCoinList.size) {
                        if (startCoinList[i].name.toLowerCase().contains(enteredText.toString().toLowerCase()) ||
                            startCoinList[i].symbol.toLowerCase().contains(enteredText.toString().toLowerCase())
                        ) {
                            searchedCoinList.add(startCoinList[i])
                        }
                    }
                    setupRecycler(searchedCoinList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

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
                    startCoinList.addAll(result)
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