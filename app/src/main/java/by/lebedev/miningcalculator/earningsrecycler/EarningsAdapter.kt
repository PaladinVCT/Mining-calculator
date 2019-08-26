package by.lebedev.miningcalculator.earningsrecycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.lebedev.domain.entities.CoinProfitability
import by.lebedev.miningcalculator.R

class EarningsAdapter(
    private val coins: ArrayList<CoinProfitability>) : RecyclerView.Adapter<EarningsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EarningsViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coin, viewGroup, false)
        val holder = EarningsViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(earningsViewHolder: EarningsViewHolder, id: Int) {

        earningsViewHolder.bind(coins.get(id))
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}