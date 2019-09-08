package by.lebedev.miningcalculator.recyclers.coinrate

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.lebedev.domain.entities.CoinRate
import by.lebedev.miningcalculator.R

class CoinRateAdapter(private val context: Context,
                      private val listCoinRate: ArrayList<CoinRate>
) : RecyclerView.Adapter<CoinRateViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CoinRateViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coinrate, viewGroup, false)
        val holder = CoinRateViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(coinRateViewHolder: CoinRateViewHolder, id: Int) {

        coinRateViewHolder.bind(listCoinRate.get(id))
    }

    override fun getItemCount(): Int {
        return listCoinRate.size
    }
}