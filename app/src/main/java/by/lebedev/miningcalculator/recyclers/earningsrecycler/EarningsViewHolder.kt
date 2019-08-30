package by.lebedev.miningcalculator.recyclers.earningsrecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import by.lebedev.domain.entities.CoinProfitabilityString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin.view.*


class EarningsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(coinProfitabilityString: CoinProfitabilityString) {

        Picasso.get().load(coinProfitabilityString.imageUrl)
            .into(itemView.coinIconImageView)

        itemView.coinNameTextView.setText(coinProfitabilityString.coinName)
        itemView.algoTextView.setText(coinProfitabilityString.algoName)
        itemView.hashPowerTextView.setText(coinProfitabilityString.hashrateAuto)
        itemView.dailyUsdTextView.setText(coinProfitabilityString.rewardDayUsd)
        itemView.dailyCoinsTextView.setText(coinProfitabilityString.rewardDayCoins)
        itemView.monthlyUsdTextView.setText(coinProfitabilityString.rewardMonthUsd)
        itemView.monthlyCoinsTextView.setText(coinProfitabilityString.rewardMonthCoins)
        itemView.volumeTextView.setText(coinProfitabilityString.volumeUsd)
    }
}