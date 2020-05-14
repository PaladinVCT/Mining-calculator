package by.lebedev.miningcalculator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import by.lebedev.miningcalculator.fragments.AmdFragment
import by.lebedev.miningcalculator.fragments.NvidiaFragment

private const val AMD = "AMD"
private const val NVIDIA = "NVIDIA"

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
            0 -> AMD
            else -> {
                return NVIDIA
            }
        }
    }
}