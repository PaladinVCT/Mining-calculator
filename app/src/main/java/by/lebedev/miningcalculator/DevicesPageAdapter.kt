package by.lebedev.miningcalculator

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import by.lebedev.miningcalculator.fragments.AmdFragment
import by.lebedev.miningcalculator.fragments.NvidiaFragment

class DevicesPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {



    override fun getItem(position: Int): Fragment {
        return when (position) {

            0 -> AmdFragment()
            else -> {
                return NvidiaFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "AMD"
            else -> {
                return "NVIDIA"
            }
        }
    }
}