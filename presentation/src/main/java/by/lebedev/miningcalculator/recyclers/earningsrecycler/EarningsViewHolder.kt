package by.lebedev.miningcalculator.recyclers.earningsrecycler

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Toast
import by.lebedev.domain.entities.CoinGeckoCoin
import by.lebedev.domain.entities.CoinProfitabilityString
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.repository.CoinTempData
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.RatesDetailActivity
import by.lebedev.miningcalculator.recyclers.coinrate.COIN_TAG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin.view.*
import java.util.*
import kotlin.collections.ArrayList


class EarningsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(coinProfitabilityString: CoinProfitabilityString) {

        Picasso.get().load(coinProfitabilityString.imageUrl)
            .into(itemView.coinIconImageView)
        itemView.apply {
            coinNameTextView.text = coinProfitabilityString.coinName
            algoTextView.text = coinProfitabilityString.algoName
            hashPowerTextView.text = coinProfitabilityString.hashrateAuto
            dailyUsdTextView.text = coinProfitabilityString.rewardDayUsd
            dailyCoinsTextView.text = coinProfitabilityString.rewardDayCoins
            monthlyUsdTextView.text = coinProfitabilityString.rewardMonthUsd
            monthlyCoinsTextView.text = coinProfitabilityString.rewardMonthCoins
            volumeTextView.text = coinProfitabilityString.volumeUsd
            if (coinProfitabilityString.showAlert) {
                volumeWarning.visibility = View.VISIBLE
            } else {
                volumeWarning.visibility = View.GONE
            }
        }

        itemView.setOnClickListener {
            val intent = Intent(it.context, RatesDetailActivity::class.java)

            val tempCoinList = ArrayList<CoinGeckoCoin>()
            for (i in 0 until CoinTempData.instance.allGeckoCoinList.size) {
                if (CoinTempData.instance.allGeckoCoinList[i].symbol.toLowerCase(Locale.ROOT) ==
                    (coinProfitabilityString.coinTicker.toLowerCase(Locale.ROOT))
                ) {
                    tempCoinList.add(CoinTempData.instance.allGeckoCoinList[i])
                }
            }

            if (tempCoinList.isNotEmpty()) {
                intent.putExtra(COIN_TAG, tempCoinList[0].id.toLowerCase(Locale.ROOT))
                CoinTempData.instance.coinTicker = tempCoinList[0].symbol.toUpperCase(Locale.ROOT)
                itemView.context.startActivity(intent)
            } else {
                Toast.makeText(
                    itemView.context,
                    it.context.getString(R.string.pools_not_found),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}