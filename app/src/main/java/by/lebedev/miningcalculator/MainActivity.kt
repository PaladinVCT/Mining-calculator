package by.lebedev.miningcalculator

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.TextView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import by.lebedev.domain.collections.AmdDevices
import by.lebedev.miningcalculator.fragments.AmdFragment
import by.lebedev.miningcalculator.fragments.DashboardFragment
import by.lebedev.miningcalculator.fragments.DevicesFragment
import by.lebedev.miningcalculator.recyclers.devicesrecycler.DevicesAdapter


class MainActivity : AppCompatActivity(), DevicesAdapter.RigInterface,AmdFragment.SetupDevicesAtStartup,AmdFragment.ClearAllDevices {



    private var back_pressed: Long = 0
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
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        setTitle("  Mining profit calculator");
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setLogo(R.drawable.logo_activity)
        getSupportActionBar()?.setDisplayUseLogoEnabled(true)

        val dashboardFragment = DashboardFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.layoutForInflate, dashboardFragment)
        ft.commit()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.dot_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.about -> {

//                val intent = Intent(this, AboutActivity::class.java)
//                startActivity(intent)
                return true
            }
            R.id.contacts -> {

//                val intent = Intent(this, ContactsActivity::class.java)
//                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            moveTaskToBack(true);
            finish();
            System.exit(0);

        } else {
            Toast.makeText(baseContext, "Press BACK again to exit", Toast.LENGTH_SHORT).show()
        }

        back_pressed = System.currentTimeMillis()
    }

    override fun setupRigDevices() {
        setNumberDevicesInRig()
        execute()
    }

    override fun execute() {
        val deviceNameLayout = findViewById<LinearLayout>(R.id.deviceNameLayout)
        val deviceCountLayout = findViewById<LinearLayout>(R.id.deviceCountLayout)

        deviceNameLayout.removeAllViews()
        deviceCountLayout.removeAllViews()

        for (i in 0 until AmdDevices.instance.list.size) {
            if (AmdDevices.instance.list.get(i).count > 0) {
                val name = TextView(this)
                name.setTypeface(null, Typeface.BOLD_ITALIC)
                name.textSize = 14f
                name.gravity = Gravity.CENTER
                name.text = AmdDevices.instance.list.get(i).name
                deviceNameLayout.addView(name)

                val count = TextView(this)
                count.setTypeface(null, Typeface.BOLD_ITALIC)
                count.textSize = 14f
                count.gravity = Gravity.CENTER

                count.text = "x ".plus(AmdDevices.instance.list.get(i).count.toString())

                deviceCountLayout.addView(count)

                setNumberDevicesInRig()
            }
        }


    }

    fun setNumberDevicesInRig() {
        val rigDeviceCounter = findViewById<TextView>(R.id.rigDeviceCounter)
        rigDeviceCounter.setText(AmdDevices.instance.devicesCount.toString().plus(" devices"))
    }

    override fun clear() {
        val deviceNameLayout = findViewById<LinearLayout>(R.id.deviceNameLayout)
        val deviceCountLayout = findViewById<LinearLayout>(R.id.deviceCountLayout)

        deviceNameLayout.removeAllViews()
        deviceCountLayout.removeAllViews()

        for (i in 0 until AmdDevices.instance.list.size){
            AmdDevices.instance.list.get(i).count = 0
        }
        AmdDevices.instance.devicesCount = 0

        setNumberDevicesInRig()

    }

}
