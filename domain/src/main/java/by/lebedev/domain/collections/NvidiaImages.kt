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
        R.drawable.nv2080ti

    )


    companion object {
        val instance = NvidiaImages()
    }
}