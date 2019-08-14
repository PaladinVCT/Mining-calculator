package by.lebedev.miningcalculator.fragments

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.dashboard_layout.*
import kotlinx.android.synthetic.main.dashboard_layout.view.*

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dashboard_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        gpuMiningText.visibility = View.INVISIBLE
        cpuMiningText.visibility = View.INVISIBLE
        gpuLayout.setBackgroundColor(0)
        cpuLayout.setBackgroundColor(0)

        gpuImage.setOnClickListener {
            gpuImage.post(Runnable {
                val gpuAnimation = gpuImage.getBackground() as AnimationDrawable
                val cpuAnimation = cpuImage.getBackground() as AnimationDrawable
                if (!gpuAnimation.isRunning) {
                    gpuAnimation.start()
                    cpuAnimation.stop()
                    gpuMiningText.visibility = View.VISIBLE
                    cpuMiningText.visibility = View.INVISIBLE
                    gpuLayout.setBackgroundResource(R.drawable.edit_text_shape)
                    cpuLayout.setBackgroundColor(0)
                }
            })
        }

        cpuImage.setOnClickListener {
            cpuImage.post(Runnable {
                val cpuAnimation = cpuImage.getBackground() as AnimationDrawable
                val gpuAnimation = gpuImage.getBackground() as AnimationDrawable
                if (!cpuAnimation.isRunning) {
                    cpuAnimation.start()
                    gpuAnimation.stop()
                    gpuMiningText.visibility = View.INVISIBLE
                    cpuMiningText.visibility = View.VISIBLE
                    cpuLayout.setBackgroundResource(R.drawable.edit_text_shape)
                    gpuLayout.setBackgroundColor(0)
                }
            })
        }


    }
}