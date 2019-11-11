package by.lebedev.miningcalculator.fragments

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.transformators.HashTypeConfigurator
import by.lebedev.miningcalculator.EarningsActivity
import by.lebedev.miningcalculator.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.dashboard_layout.*

class DashboardFragment : Fragment() {

    lateinit var mAdView: AdView

    val gpu = "GPU"
    val cpu = "CPU"
    val asic = "ASIC"

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

        MobileAds.initialize(this.context) {}

        mAdView = adViewDashboard
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val arrayOfGpuAlgos = arrayOfNulls<String>(algos.gpuList.size)
        algos.gpuList.toArray(arrayOfGpuAlgos)
        val arrayOfAsicAlgos = arrayOfNulls<String>(algos.asicList.size)
        algos.asicList.toArray(arrayOfAsicAlgos)

        gpuMiningText.visibility = View.INVISIBLE
        cpuMiningText.visibility = View.INVISIBLE
        asicMiningText.visibility = View.INVISIBLE

        gpuLayout.setBackgroundColor(0)
        cpuLayout.setBackgroundColor(0)
        asicLayout.setBackgroundColor(0)

        gpuImage.setOnClickListener {
            gpuImage.post(Runnable {
                val asicAnimation = asicImage.getBackground() as AnimationDrawable
                val gpuAnimation = gpuImage.getBackground() as AnimationDrawable
                val cpuAnimation = cpuImage.getBackground() as AnimationDrawable
                if (!gpuAnimation.isRunning) {
                    gpuAnimation.start()
                    cpuAnimation.stop()
                    asicAnimation.stop()
                    gpuMiningText.visibility = View.VISIBLE
                    cpuMiningText.visibility = View.INVISIBLE
                    asicMiningText.visibility = View.INVISIBLE
                    gpuLayout.setBackgroundResource(R.drawable.edit_text_shape)
                    cpuLayout.setBackgroundColor(0)
                    asicLayout.setBackgroundColor(0)
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
                val asicAnimation = asicImage.getBackground() as AnimationDrawable
                val cpuAnimation = cpuImage.getBackground() as AnimationDrawable
                val gpuAnimation = gpuImage.getBackground() as AnimationDrawable
                if (!cpuAnimation.isRunning) {
                    cpuAnimation.start()
                    gpuAnimation.stop()
                    asicAnimation.stop()
                    gpuMiningText.visibility = View.INVISIBLE
                    asicMiningText.visibility = View.INVISIBLE
                    cpuMiningText.visibility = View.VISIBLE
                    cpuLayout.setBackgroundResource(R.drawable.edit_text_shape)
                    gpuLayout.setBackgroundColor(0)
                    asicLayout.setBackgroundColor(0)
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

        asicImage.setOnClickListener {
            asicImage.post(Runnable {
                val asicAnimation = asicImage.getBackground() as AnimationDrawable
                val cpuAnimation = cpuImage.getBackground() as AnimationDrawable
                val gpuAnimation = gpuImage.getBackground() as AnimationDrawable
                if (!asicAnimation.isRunning) {
                    asicAnimation.start()
                    gpuAnimation.stop()
                    cpuAnimation.stop()
                    gpuMiningText.visibility = View.INVISIBLE
                    cpuMiningText.visibility = View.INVISIBLE
                    asicMiningText.visibility = View.VISIBLE
                    asicLayout.setBackgroundResource(R.drawable.edit_text_shape)
                    gpuLayout.setBackgroundColor(0)
                    cpuLayout.setBackgroundColor(0)
                    algoSelectorButton.setEnabled(true)
                    calculateButton.setEnabled(false)
                    algoSelectorButton.text = resources.getText(R.string.select_mining_algo)
                    cryptonightInfoTextView.visibility = View.INVISIBLE
                    algos.selectedAlgo = ""
                    hashTypeTextView.setText("H/s")
                    device = asic
                }
            })
        }

        algoSelectorButton.setOnClickListener {
            if (device.equals(cpu) || device.equals(gpu)) {
                buildAlgoSelector(arrayOfGpuAlgos)
            } else {
                buildAlgoSelector(arrayOfAsicAlgos)
            }
        }


        calculateButton.setOnClickListener {

            if (hashrateEditText.text.isEmpty()||
                   energyEditText.text.isEmpty()||
                energyCostEditText.text.isEmpty()||
                poolFeeEditText.text.isEmpty()){
                Toast.makeText(
                    view.context, "Please fill in ALL the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else {

                val intent = Intent(this.context, EarningsActivity::class.java)
                intent.putExtra("selectedItem", selectedItem)
                intent.putExtra("hashrate", hashrateEditText.text.toString().replace(',', '.').toDouble())
                intent.putExtra("device", device)
                intent.putExtra("energy", energyEditText.text.toString().replace(',', '.').toDouble())
                intent.putExtra("energyCost", energyCostEditText.text.toString().replace(',', '.').toDouble())
                intent.putExtra("fee", poolFeeEditText.text.toString().replace(',', '.').toDouble())

                it.context.startActivity(intent)
            }
        }

    }

    fun buildAlgoSelector(algosArray: Array<String?>) {
        val builder = AlertDialog.Builder(this.context!!)
            .setTitle("Select mining algo")
            .setIcon(R.drawable.algo_icon)
            .setCancelable(true)

            .setSingleChoiceItems(algosArray, -1,
                { _, item ->
                    selectedItem = item
                })
            .setPositiveButton("OK", { dialog, _ ->
                if (selectedItem != -1) {
                    algos.selectedAlgo = algosArray[selectedItem]!!
                    algoSelectorButton.setText(algosArray[selectedItem])
                    hashTypeTextView.setText(HashTypeConfigurator().getTypeFromName(algosArray[selectedItem]!!))
                    calculateButton.setEnabled(true)
                }
                if (selectedItem == 0 && !device.equals(asic)) {
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
}