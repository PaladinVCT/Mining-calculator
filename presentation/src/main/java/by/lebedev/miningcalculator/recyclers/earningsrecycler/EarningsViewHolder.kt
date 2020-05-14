package by.lebedev.miningcalculator.recyclers.earningsrecycler

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import by.lebedev.domain.entities.CoinProfitabilityString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin.view.*


class EarningsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(coinProfitabilityString: CoinProfitabilityString) {

        Picasso.get().load(coinProfitabilityString.imageUrl)
            .into(itemView.coinIconImageView)

        itemView.coinNameTextView.text = coinProfitabilityString.coinName
        itemView.algoTextView.text = coinProfitabilityString.algoName
        itemView.hashPowerTextView.text = coinProfitabilityString.hashrateAuto
        itemView.dailyUsdTextView.text = coinProfitabilityString.rewardDayUsd
        itemView.dailyCoinsTextView.text = coinProfitabilityString.rewardDayCoins
        itemView.monthlyUsdTextView.text = coinProfitabilityString.rewardMonthUsd
        itemView.monthlyCoinsTextView.text = coinProfitabilityString.rewardMonthCoins
        itemView.volumeTextView.text = coinProfitabilityString.volumeUsd
        if (coinProfitabilityString.showAlert) {
            itemView.volumeWarning.visibility = View.VISIBLE
        } else {
            itemView.volumeWarning.visibility = View.GONE
        }
    }
}