package by.lebedev.miningcalculator

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.logo_layout.*

const val ADS = "ads"
const val ADS_PREF = "ads_pref"

class LogoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logo_layout)

        val prefs = getSharedPreferences(ADS_PREF, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(ADS, true).commit()

        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle)
        title = " "
        supportActionBar?.hide()

        val mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        mFadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        logoImage.startAnimation(mFadeInAnimation)
    }


}