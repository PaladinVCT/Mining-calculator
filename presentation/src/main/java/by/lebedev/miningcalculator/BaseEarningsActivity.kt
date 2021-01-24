package by.lebedev.miningcalculator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.earnings_layout.*

abstract class BaseEarningsActivity : AppCompatActivity() {

    protected lateinit var mInterstitialAd: InterstitialAd
    protected lateinit var adRequest: AdRequest
    protected lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earnings_layout)

        MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.interstitial_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        prefs = getSharedPreferences(ADS_PREF, Context.MODE_PRIVATE)
    }
}