package by.lebedev.miningcalculator.recyclers.coinrate

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.domain.entities.CoinData
import by.lebedev.domain.entities.CoinRate
import by.lebedev.domain.repository.CoinTempData
import by.lebedev.miningcalculator.R
import by.lebedev.miningcalculator.RatesDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coinrate.view.*
import java.text.NumberFormat

const val COIN_TAG = "Coin_tag"

class CoinRateAdapter(val list: List<CoinRate>) : RecyclerView.Adapter<CoinRateViewHolder>() {

    private val nf = NumberFormat.getInstance()


    var mData: List<CoinData>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinRateViewHolder {

        return CoinRateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_coinrate,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CoinRateViewHolder, position: Int) {
        nf.maximumFractionDigits = 2
        list.let {
            val coin = it[position]
            holder.view.tvSymbol.text = coin.symbol
            holder.view.tvName.text = coin.name
            holder.view.tvPriceChange.text = coin.changePercent24Hr
            holder.view.tvPriceChange.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    coin.changePercent24HrColor
                )
            )
            holder.view.tvPrice.text = coin.priceUsd.toString().plus("$")

            Picasso.get().load(coin.url).error(R.drawable.nocoinlogo)
                .into(holder.view.url)

            holder.itemView.setOnClickListener { view ->
                val intent = Intent(view.context, RatesDetailActivity::class.java)
                intent.putExtra(COIN_TAG, coin.id)
                CoinTempData.instance.coinTicker = coin.symbol
                view.context.startActivity(intent)

            }
        }
    }
}