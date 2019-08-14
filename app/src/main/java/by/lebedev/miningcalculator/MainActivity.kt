package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.graphics.drawable.AnimationDrawable
import android.view.Menu
import android.view.MenuItem
import by.lebedev.miningcalculator.fragments.DashboardFragment
import kotlinx.android.synthetic.main.dashboard_layout.*
import kotlinx.android.synthetic.main.main_layout.*


class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                val dashboardFragment = DashboardFragment()
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.layoutForInflate, dashboardFragment)
                ft.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
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
}
