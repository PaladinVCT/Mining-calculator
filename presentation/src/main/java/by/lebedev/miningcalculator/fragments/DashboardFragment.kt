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
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.dashboard_layout.*

private const val GPU = "GPU"
private const val CPU = "CPU"
private const val ASIC = "ASIC"
private const val CRYPTONIGHT = "Cryptonight"
const val ENERGY = "energy"
const val ENERGY_COST = "energyCost"
const val FEE = "fee"

class DashboardFragment : Fragment() {
    private lateinit var mInterstitialAd: InterstitialAd
    lateinit var mAdView: AdView

    var selectedItem = -1
    var device = " "

    private val algos = Algos.instance


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MobileAds.initialize(this.context) {}

        mInterstitialAd = InterstitialAd(requireContext())
        mInterstitialAd.adUnitId = resources.getString(R.string.interstitial_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

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
                val asicAnimation = asicImage.background as AnimationDrawable
                val gpuAnimation = gpuImage.background as AnimationDrawable
                val cpuAnimation = cpuImage.background as AnimationDrawable
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
                    algoSelectorButton.isEnabled = true
                    calculateButton.isEnabled = false
                    algoSelectorButton.text = resources.getText(R.string.select_mining_algo)
                    cryptonightInfoTextView.visibility = View.INVISIBLE
                    algos.selectedAlgo = ""
                    hashTypeTextView.text = getString(R.string.HashSec)
                    device = GPU
                }
            })
        }

        cpuImage.setOnClickListener {
            cpuImage.post(Runnable {
                val asicAnimation = asicImage.background as AnimationDrawable
                val cpuAnimation = cpuImage.background as AnimationDrawable
                val gpuAnimation = gpuImage.background as AnimationDrawable
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
                    algoSelectorButton.isEnabled = false
                    algoSelectorButton.text = CRYPTONIGHT
                    algos.selectedAlgo = CRYPTONIGHT
                    selectedItem = 0
                    hashTypeTextView.text = getString(R.string.HashSec)
                    device = CPU
                    cryptonightInfoTextView.visibility = View.VISIBLE
                    calculateButton.isEnabled = true
                }
            })
        }

        asicImage.setOnClickListener {
            asicImage.post(Runnable {
                val asicAnimation = asicImage.background as AnimationDrawable
                val cpuAnimation = cpuImage.background as AnimationDrawable
                val gpuAnimation = gpuImage.background as AnimationDrawable
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
                    algoSelectorButton.isEnabled = true
                    calculateButton.isEnabled = false
                    algoSelectorButton.text = resources.getText(R.string.select_mining_algo)
                    cryptonightInfoTextView.visibility = View.INVISIBLE
                    algos.selectedAlgo = ""
                    hashTypeTextView.text = getString(R.string.HashSec)
                    device = ASIC
                }
            })
        }

        algoSelectorButton.setOnClickListener {
            if (device == CPU || device == GPU) {
                buildAlgoSelector(arrayOfGpuAlgos)
            } else {
                buildAlgoSelector(arrayOfAsicAlgos)
            }
        }


        calculateButton.setOnClickListener {

            if (hashrateEditText.text.isEmpty() ||
                energyEditText.text.isEmpty() ||
                energyCostEditText.text.isEmpty() ||
                poolFeeEditText.text.isEmpty()
            ) {
                Toast.makeText(
                    view.context, getString(R.string.please_fill_all_fields),
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val intent = Intent(this.context, EarningsActivity::class.java)
                intent.putExtra(SELECTED_ITEM, selectedItem)
                intent.putExtra(
                    HASHRATE,
                    hashrateEditText.text.toString().replace(',', '.').toDouble()
                )
                intent.putExtra(DEVICE, device)
                intent.putExtra(
                    ENERGY,
                    energyEditText.text.toString().replace(',', '.').toDouble()
                )
                intent.putExtra(
                    ENERGY_COST,
                    energyCostEditText.text.toString().replace(',', '.').toDouble()
                )
                intent.putExtra(FEE, poolFeeEditText.text.toString().replace(',', '.').toDouble())

                mInterstitialAd.show()

                it.context.startActivity(intent)
            }
        }

    }

    private fun buildAlgoSelector(algosArray: Array<String?>) {
        val builder = AlertDialog.Builder(this.context!!)
            .setTitle(getString(R.string.select_mining_algo))
            .setIcon(R.drawable.algo_icon)
            .setCancelable(true)

            .setSingleChoiceItems(algosArray, -1
            ) { _, item ->
                selectedItem = item
            }
            .setPositiveButton(getString(R.string.OK)) { dialog, _ ->
                if (selectedItem != -1) {
                    algos.selectedAlgo = algosArray[selectedItem]!!
                    algoSelectorButton.text = algosArray[selectedItem]
                    hashTypeTextView.text = HashTypeConfigurator().getTypeFromName(algosArray[selectedItem]!!)
                    calculateButton.isEnabled = true
                }
                if (selectedItem == 0 && !device.equals(ASIC)) {
                    cryptonightInfoTextView.visibility = View.VISIBLE
                } else cryptonightInfoTextView.visibility = View.INVISIBLE

                dialog.cancel()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->


                dialog.cancel()
            }
        val alert = builder.create()
        alert.show()
    }
}