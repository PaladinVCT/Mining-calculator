package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.donation_layout.*
import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import android.R.attr.data
import android.app.PendingIntent.getActivity


class DonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_layout)


        val btcWallet = btcWalletTextView.text.toString()


        btcWalletTextView.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("BTC", btcWallet)
            clipboard.setPrimaryClip(clip)

            Toast.makeText(
                this, "BTC address copied to buffer",
                Toast.LENGTH_SHORT
            ).show()

        }

    }
}