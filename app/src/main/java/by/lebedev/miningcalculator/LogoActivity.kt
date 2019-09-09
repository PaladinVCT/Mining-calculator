package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class LogoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logo_layout)
        setTitle(" ");
        getSupportActionBar()?.hide()

        sleepAndTransit()
    }

    private fun sleepAndTransit() {

        android.os.Handler().postDelayed({ val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.abc_fade_in,  R.anim.abc_fade_out) }, 1500)

    }
}  