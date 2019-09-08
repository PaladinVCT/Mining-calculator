package by.lebedev.miningcalculator.recyclers.coinrate

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import by.lebedev.domain.entities.CoinRate
import by.lebedev.miningcalculator.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coinrate.view.*

class CoinRateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(coinRate: CoinRate) {

        Picasso.get().load(coinRate.logoUrl)
            .into(itemView.coinLogo)

        itemView.coinName.setText(coinRate.name)
        itemView.coinTicker.setText(coinRate.symbol)
        if (coinRate.percent_change_24h > 0) {
            itemView.coinPriceChange.setText("+" + coinRate.percent_change_24h.toString().plus("%"))
            itemView.coinPriceChange.setTextColor(Color.parseColor("#37B110"))
        } else {
            itemView.coinPriceChange.setText(coinRate.percent_change_24h.toString().plus("%"))
            itemView.coinPriceChange.setTextColor(Color.parseColor("#D41611"))
        }
        itemView.coinPrice.setText(coinRate.price.toString().plus(" $"))


    }
}