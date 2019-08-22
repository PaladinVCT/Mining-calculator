package by.lebedev.miningcalculator.coinresultrecycler

import android.support.v7.widget.RecyclerView
import android.view.View
import by.lebedev.domain.entities.CoinProfitability
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin.view.*


class CoinsResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(coinProfitability: CoinProfitability) {

        Picasso.get().load(coinProfitability.imageUrl)
            .into(itemView.coinIconImageView)

        itemView.coinNameTextView.setText(coinProfitability.coinName)
        itemView.algoTextView.setText(coinProfitability.algoName)
        itemView.hashPowerTextView.setText(coinProfitability.hashrate.toString())
        itemView.dailyUsdTextView.setText(coinProfitability.rewardDayUsd.toString())
        itemView.dailyCoinsTextView.setText(coinProfitability.rewardDayCoins.toString())
        itemView.monthlyUsdTextView.setText(coinProfitability.rewardMonthUsd.toString())
        itemView.monthlyCoinsTextView.setText(coinProfitability.rewardMonthCoins.toString())
        itemView.volumeTextView.setText(coinProfitability.volumeUsd.toString())
    }
}