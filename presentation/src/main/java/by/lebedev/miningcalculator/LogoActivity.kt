package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import by.lebedev.domain.repository.CoinTempData
import by.lebedev.domain.usecase.GetCoinCapRatesUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.coinrates_layout.*
import kotlinx.android.synthetic.main.logo_layout.*


class LogoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logo_layout)

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