package by.lebedev.miningcalculator.coinresultrecycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.miningcalculator.R

class CoinsResultAdapter(
    private val coins: ArrayList<CoinProfitability>) : RecyclerView.Adapter<CoinsResultViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CoinsResultViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coin, viewGroup, false)
        val holder = CoinsResultViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(coinsResultViewHolder: CoinsResultViewHolder, id: Int) {

        coinsResultViewHolder.bind(coins.get(id))
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}