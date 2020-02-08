package by.lebedev.miningcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.donations_layout.*


class DonationActivity : AppCompatActivity(), RewardedVideoAdListener {

    lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdLeftApplication() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdLoaded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdOpened() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoCompleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        Toast.makeText(
            this, "Video not ready yet",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onRewarded(p0: RewardItem?) {
        Toast.makeText(
            this, "Thanks for watching video and your support. Have a nice day!",
            Toast.LENGTH_LONG
        ).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donations_layout)
        MobileAds.initialize(this, getString(R.string.app_id))
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.setRewardedVideoAdListener(this)
        loadRewardedVideoAd()

        watchAdsButton.setOnClickListener {
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }
    }

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(
            getString(R.string.rewarded_id),
            AdRequest.Builder().build()
        )
    }
}