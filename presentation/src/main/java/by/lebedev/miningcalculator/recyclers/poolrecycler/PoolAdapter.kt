package by.lebedev.miningcalculator.recyclers.poolrecycler

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.Image
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.domain.entities.PoolsItem
import by.lebedev.miningcalculator.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pool.view.*

class PoolAdapter(val poolList: ArrayList<PoolsItem>) :
    RecyclerView.Adapter<PoolAdapter.PoolViewHolder>() {


    class PoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoolViewHolder {
        return PoolViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pool, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return poolList.size
    }

    override fun onBindViewHolder(holder: PoolViewHolder, position: Int) {

        val view = holder.itemView

        view.poolNameTextView.text = poolList[position].name
        view.averageFeeTextView.text = view.context.getString(R.string.average_pool_fee).plus(" ")
            .plus(poolList[position].averageFee)
        view.poolRatingTextView.text = poolList[position].rating.avg.toString()
        Picasso.get().load(poolList[position].logoUrl).into(view.poolIconImageView)

        view.setOnClickListener {

            val layout =
                LayoutInflater.from(view.context).inflate(R.layout.pool_details_layout, null)
            val poolName = layout.findViewById<TextView>(R.id.poolNameTextView)
            val poolRating = layout.findViewById<TextView>(R.id.poolRatingTextView)
            val poolIcon = layout.findViewById<ImageView>(R.id.poolIconImageView)
            val poolFee = layout.findViewById<TextView>(R.id.poolFeeTextView)
            val poolLocation = layout.findViewById<TextView>(R.id.poolLocationTextView)
            val poolPaymentType = layout.findViewById<TextView>(R.id.poolPaymentTypeTextView)
            val poolWebsite = layout.findViewById<TextView>(R.id.poolWebsite)
            val poolCoinList = layout.findViewById<TextView>(R.id.poolCoinList)

            poolName.text = poolList[position].name
            poolFee.text = poolList[position].averageFee
            poolRating.text = poolList[position].rating.avg.toString()

            poolList[position].serverLocations.forEach { server ->
                poolLocation.text = poolLocation.text.toString().plus(server).plus("  ")
            }
            poolList[position].paymentType.forEach { paymentType ->
                poolPaymentType.text = poolPaymentType.text.toString().plus(paymentType).plus("  ")
            }
            poolList[position].coins.forEach { paymentType ->
                poolCoinList.text = poolCoinList.text.toString().plus(paymentType).plus("  ")
            }
            poolWebsite.text = poolList[position].affiliateURL
            Picasso.get().load(poolList[position].logoUrl).into(poolIcon)


            AlertDialog.Builder(it.context).setView(layout)
                .setPositiveButton(
                    "rate pool"
                ) { _, _ ->
                    val ratingBar = layout.findViewById<RatingBar>(R.id.ratingBar)

                    Toast.makeText(it.context, "Thanks for voting!", Toast.LENGTH_SHORT)
                        .show()

                }
                .setNegativeButton("close") { _, _ ->
                }
                .create().show()
        }
    }

}