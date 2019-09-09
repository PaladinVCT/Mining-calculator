package by.lebedev.miningcalculator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.devices_layout.*


class DevicesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.devices_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inflateAmd()


        amdLayout.setOnClickListener {
            amdLayout.setBackgroundResource(R.drawable.vendor_shape)
            nvidiaLayout.setBackgroundColor(0)
            inflateAmd()
        }

        nvidiaLayout.setOnClickListener {
            nvidiaLayout.setBackgroundResource(R.drawable.vendor_shape)
            amdLayout.setBackgroundColor(0)
            inflateNvidia()
        }

    }

    fun inflateAmd() {
        val amdFragment = AmdFragment()

        val ft = childFragmentManager.beginTransaction()
        ft.replace(R.id.rigConstructorLayout, amdFragment)
        ft.commit()
    }

    fun inflateNvidia() {
        val nvidiaFragment = NvidiaFragment()
        val ft = childFragmentManager.beginTransaction()
        ft.replace(R.id.rigConstructorLayout, nvidiaFragment)
        ft.commit()
    }

}