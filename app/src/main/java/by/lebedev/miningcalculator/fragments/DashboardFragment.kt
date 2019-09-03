package by.lebedev.miningcalculator.fragments

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.transformators.HashTypeConfigurator
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.R
import kotlinx.android.synthetic.main.dashboard_layout.*

class DashboardFragment : Fragment() {

    val gpu = "GPU"
    val cpu = "CPU"

    var selectedItem = -1
    var device = " "
    val cryptonight = "Cryptonight"

    val algos = Algos.instance


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dashboard_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayOfAlgos = arrayOfNulls<String>(algos.list.size)
        algos.list.toArray(arrayOfAlgos)

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
                    algoSelectorButton.setEnabled(true)
                    calculateButton.setEnabled(false)
                    algoSelectorButton.text = resources.getText(R.string.select_mining_algo)
                    cryptonightInfoTextView.visibility = View.INVISIBLE
                    algos.selectedAlgo = ""
                    hashTypeTextView.setText("H/s")
                    device = gpu
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
                    algoSelectorButton.setEnabled(false)
                    algoSelectorButton.setText(cryptonight)
                    algos.selectedAlgo = cryptonight
                    selectedItem = 0
                    hashTypeTextView.setText("H/s")
                    device = cpu
                    cryptonightInfoTextView.visibility = View.VISIBLE
                    calculateButton.setEnabled(true)
                }
            })
        }

        algoSelectorButton.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
                .setTitle("Select mining algo")
                .setIcon(R.drawable.algo_icon)
                .setCancelable(true)

                .setSingleChoiceItems(arrayOfAlgos, -1,
                    { _, item ->

                        selectedItem = item

                    })


                .setPositiveButton("OK", { dialog, _ ->
                    if (selectedItem != -1) {
                        algos.selectedAlgo = arrayOfAlgos[selectedItem]!!
                        algoSelectorButton.setText(arrayOfAlgos[selectedItem])
                        hashTypeTextView.setText(HashTypeConfigurator().getTypeFromName(arrayOfAlgos[selectedItem]!!))
                        calculateButton.setEnabled(true)
                    }
                    if (selectedItem == 0) {
                        cryptonightInfoTextView.visibility = View.VISIBLE
                    } else cryptonightInfoTextView.visibility = View.INVISIBLE

                    dialog.cancel()
                })
                .setNegativeButton("Cancel", { dialog, _ ->


                    dialog.cancel()
                })
            val alert = builder.create()
            alert.show()
        }


        calculateButton.setOnClickListener {

            val intent = Intent(this.context,EarningsActivity::class.java)
            intent.putExtra("selectedItem",selectedItem)
            intent.putExtra("hashrate",hashrateEditText.text.toString().toDouble())
            intent.putExtra("device",device)
            intent.putExtra("energy",energyEditText.text.toString().toDouble())
            intent.putExtra("energyCost",energyCostEditText.text.toString().toDouble())
            intent.putExtra("fee",poolFeeEditText.text.toString().toDouble())

            it.context.startActivity(intent)
        }








    }
}