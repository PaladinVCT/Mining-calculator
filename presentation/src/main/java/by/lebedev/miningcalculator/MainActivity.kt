package by.lebedev.miningcalculator

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import by.lebedev.domain.collections.VendorDevices
import by.lebedev.miningcalculator.fragments.*
import by.lebedev.miningcalculator.recyclers.devicesrecycler.amd.DevicesAdapterAMD
import by.lebedev.miningcalculator.recyclers.devicesrecycler.nvidia.DevicesAdapterNvidia
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import android.text.InputType
import by.lebedev.domain.usecase.SaveAmdConfigUseCaseImpl
import by.lebedev.domain.usecase.SaveNvidiaConfigUseCaseImpl
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), DevicesAdapterAMD.InitialRigSetup, AmdFragment.SetupDevices,
    AmdFragment.ClearAllDevices, DevicesAdapterNvidia.InitialRigSetup,
    NvidiaFragment.SetupDevices, NvidiaFragment.ClearAllDevices, AmdFragment.SaveConfigAMD, NvidiaFragment.SaveConfigNvidia {

    private lateinit var mInterstitialAd: InterstitialAd
    private var backPressed: Long = 0
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                val dashboardFragment = DashboardFragment()
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.layoutForInflate, dashboardFragment)
                ft.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.hardware -> {

                val devicesFragment = DevicesFragment()
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.layoutForInflate, devicesFragment)
                ft.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.coinRates -> {
                val coinRatesFragment = CoinRatesFragment()
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.layoutForInflate, coinRatesFragment)
                ft.commit()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        MobileAds.initialize(this) {}

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = resources.getString(R.string.interstitial_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        title = getString(R.string.mining_profit_calculator)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.logo_activity)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        val dashboardFragment = DashboardFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.layoutForInflate, dashboardFragment)
        ft.commit()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dot_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.support -> {
                mInterstitialAd.loadAd(AdRequest.Builder().build())

                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()

                } else {
                    Log.e(TAG, getString(R.string.interstitial_not_loaded_yet))
                }

                val intent = Intent(this, DonationActivity::class.java)
                startActivity(intent)

                return true
            }
            R.id.feedback -> {

                val email = Intent(Intent.ACTION_SEND)
                email.type = getString(R.string.text_email)
                email.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.my_email)))
                email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_mining_app))
                email.putExtra(Intent.EXTRA_TEXT, getString(R.string.dear_me) + "\n")
                startActivity(Intent.createChooser(email, getString(R.string.send_feedback)))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            moveTaskToBack(true);
            finish();
            exitProcess(0);

        } else {
            Toast.makeText(baseContext, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show()
        }

        backPressed = System.currentTimeMillis()
    }

    override fun setupRigDevices(
        instance: VendorDevices,
        deviceNameLayoutId: Int,
        deviceCountLayoutId: Int,
        counterTextView: Int
    ) {
        setNumberDevicesInRig(instance, counterTextView)
        setupAtStartup(instance, deviceNameLayoutId, deviceCountLayoutId, counterTextView)
    }

    override fun setupAtStartup(
        instance: VendorDevices,
        deviceNameLayoutId: Int,
        deviceCountLayoutId: Int,
        counterTextView: Int
    ) {
        val deviceNameLayout = findViewById<LinearLayout>(deviceNameLayoutId)
        val deviceCountLayout = findViewById<LinearLayout>(deviceCountLayoutId)

        deviceNameLayout.removeAllViews()
        deviceCountLayout.removeAllViews()

        for (i in 0 until instance.list.size) {
            if (instance.list.get(i).count > 0) {
                val name = TextView(this)
                name.setTypeface(null, Typeface.BOLD_ITALIC)
                name.textSize = 14f
                name.gravity = Gravity.CENTER
                name.text = instance.list.get(i).name
                deviceNameLayout.addView(name)

                val count = TextView(this)
                count.setTypeface(null, Typeface.BOLD_ITALIC)
                count.textSize = 14f
                count.gravity = Gravity.CENTER

                count.text = "x ".plus(instance.list.get(i).count.toString())

                deviceCountLayout.addView(count)

                setNumberDevicesInRig(instance, counterTextView)
            }
        }


    }

    private fun setNumberDevicesInRig(instance: VendorDevices, counterTextView: Int) {
        val rigDeviceCounter = findViewById<TextView>(counterTextView)
        rigDeviceCounter.text = instance.devicesCount.toString().plus(getString(R.string.devices))
    }

    override fun clear(
        instance: VendorDevices,
        deviceNameLayoutId: Int,
        deviceCountLayoutId: Int,
        counterTextView: Int
    ) {
        val deviceNameLayout = findViewById<LinearLayout>(deviceNameLayoutId)
        val deviceCountLayout = findViewById<LinearLayout>(deviceCountLayoutId)

        deviceNameLayout.removeAllViews()
        deviceCountLayout.removeAllViews()

        for (i in 0 until instance.list.size) {
            instance.list.get(i).count = 0
        }
        instance.devicesCount = 0

        setNumberDevicesInRig(instance, counterTextView)

        Toast.makeText(
            this, getString(R.string.cleared),
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun saveAMD() {

        val input = EditText(this)
        input.hint = getString(R.string.configuration_name)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE

        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.save_this_config))
            .setIcon(R.drawable.rigicon)
            .setView(input)
            .setCancelable(true)
            .setPositiveButton(getString(R.string.save)) { dialog, _ ->

                val name = input.text.toString()

                if (name.isEmpty()) {
                    Toast.makeText(
                        this, getString(R.string.please_enter_config_name),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    SaveAmdConfigUseCaseImpl().execute(this, name)

                    Toast.makeText(
                        this, getString(R.string.configuration_saved),
                        Toast.LENGTH_SHORT
                    ).show()

                    dialog.cancel()
                }
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->

                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()

    }

    override fun saveNvidia() {

        val input = EditText(this)
        input.hint = getString(R.string.configuration_name)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE

        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.save_this_configuration))
            .setIcon(R.drawable.rigicon)
            .setView(input)
            .setCancelable(true)
            .setPositiveButton(getString(R.string.save)) { dialog, _ ->

                val name = input.text.toString()

                if (name.isEmpty()) {
                    Toast.makeText(
                        this, getString(R.string.please_enter_config_name),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    SaveNvidiaConfigUseCaseImpl().execute(this, name)

                    Toast.makeText(
                        this, getString(R.string.config_saved),
                        Toast.LENGTH_SHORT
                    ).show()

                    dialog.cancel()

                    Toast.makeText(
                        this, getString(R.string.config_saved),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->

                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()
    }
}