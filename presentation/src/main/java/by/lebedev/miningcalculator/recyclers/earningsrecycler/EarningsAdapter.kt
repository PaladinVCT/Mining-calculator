package by.lebedev.miningcalculator.recyclers.earningsrecycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.lebedev.domain.entities.CoinProfitabilityString
import by.lebedev.miningcalculator.R

class EarningsAdapter(
    private val coins: ArrayList<CoinProfitabilityString>
) : RecyclerView.Adapter<EarningsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EarningsViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coin, viewGroup, false)
        return EarningsViewHolder(view)
    }

    override fun onBindViewHolder(earningsViewHolder: EarningsViewHolder, id: Int) {

        earningsViewHolder.bind(coins[id])
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}