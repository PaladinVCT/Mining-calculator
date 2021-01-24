package by.lebedev.domain.collections

import by.lebedev.domain.R
import java.util.*

class NvidiaImages {

    val list: ArrayList<Int> = arrayListOf(
        R.drawable.nv1050ti,
        R.drawable.nv1060,
        R.drawable.nv1660,
        R.drawable.nv1660ti,
        R.drawable.nv1070,
        R.drawable.nv1070ti,
        R.drawable.nv1080,
        R.drawable.nv1080ti,
        R.drawable.nv2060,
        R.drawable.nv2070,
        R.drawable.nv2080,
        R.drawable.nv2080ti,
        R.drawable.gtxtitanx,
        R.drawable.nv102100,
        R.drawable.nv104100,
        R.drawable.nv106100,
        R.drawable.p100tesla,
        R.drawable.nv3060ti,
        R.drawable.nv3070,
        R.drawable.nv3080
    )

    companion object {
        val instance = NvidiaImages()
    }
}