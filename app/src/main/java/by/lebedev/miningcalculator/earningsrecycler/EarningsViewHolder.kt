package by.lebedev.miningcalculator.earningsrecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import by.lebedev.domain.entities.CoinProfitability
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin.view.*


class EarningsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(coinProfitability: CoinProfitability) {

        Picasso.get().load(coinProfitability.imageUrl)
            .into(itemView.coinIconImageView)

        itemView.coinNameTextView.setText(coinProfitability.coinName)
        itemView.algoTextView.setText(coinProfitability.algoName)
        itemView.hashPowerTextView.setText(coinProfitability.hashrateAuto)
        itemView.dailyUsdTextView.setText(coinProfitability.rewardDayUsd)
        itemView.dailyCoinsTextView.setText(coinProfitability.rewardDayCoins)
        itemView.monthlyUsdTextView.setText(coinProfitability.rewardMonthUsd)
        itemView.monthlyCoinsTextView.setText(coinProfitability.rewardMonthCoins)
        itemView.volumeTextView.setText(coinProfitability.volumeUsd)
    }
}