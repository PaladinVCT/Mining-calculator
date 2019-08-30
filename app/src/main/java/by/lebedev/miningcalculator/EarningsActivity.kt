package by.lebedev.miningcalculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import by.lebedev.domain.collections.Algos
import by.lebedev.domain.transformators.CoinProfitabilityEnergyFeeCalculator
import by.lebedev.domain.transformators.CoinProfitabilityStringTransformator
import by.lebedev.domain.entities.CoinProfitabilityString
import by.lebedev.domain.transformators.HashTypeConfigurator
import by.lebedev.domain.usecase.GetProfitableCoinsUseCaseCryptonight
import by.lebedev.domain.usecase.GetProfitableCoinsUseCaseNvidia
import by.lebedev.miningcalculator.recyclers.earningsrecycler.EarningsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.earnings_layout.*

class EarningsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earnings_layout)

        val selectedItem = intent.getIntExtra("selectedItem", -1)


        val hashrate = intent.getLongExtra("hashrate", -1)*HashTypeConfigurator().getDigitsFromType(HashTypeConfigurator().getTypeFromName(
            Algos.instance.list.get(selectedItem)))
        val device = intent.getStringExtra("device")
        val energy = intent.getDoubleExtra("energy", 0.0)
        val energyCost = intent.getDoubleExtra("energyCost", 0.0)
        val fee = intent.getDoubleExtra("fee", 0.0)

        if (selectedItem == 0) {
            getEarningsCryptonight(selectedItem, hashrate, device, energy, energyCost, fee)
        } else {
            getEarningsNvidia(selectedItem, hashrate, device, energy, energyCost, fee)
        }
    }

    fun getEarningsCryptonight(
        selectedItem: Int,
        hashrate: Long,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseCryptonight().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("AAA", result.get(0).toString())

                earningsProgressBar.visibility = View.INVISIBLE
                val profit = result
                val profitMinusFee = CoinProfitabilityEnergyFeeCalculator()
                    .execute(profit, energy, energyCost, fee)
                val profitArrayString = CoinProfitabilityStringTransformator().execute(profitMinusFee)
                setupRecycler(profitArrayString)


            }, {
                Log.e("AAA", it.message)
            })


    }

    fun getEarningsNvidia(
        selectedItem: Int,
        hashrate: Long,
        device: String,
        energy: Double,
        energyCost: Double,
        fee: Double
    ) {

        val d = GetProfitableCoinsUseCaseNvidia().fetch(selectedItem, hashrate, device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("AAA", result.get(0).toString())

                earningsProgressBar.visibility = View.INVISIBLE
                val profit = result
                val profitMinusFee = CoinProfitabilityEnergyFeeCalculator()
                    .execute(profit, energy, energyCost, fee)
                val profitArrayString = CoinProfitabilityStringTransformator().execute(profitMinusFee)
                setupRecycler(profitArrayString)


            }, {
                Log.e("AAA", it.message)
            })


    }


    fun setupRecycler(coinList: ArrayList<CoinProfitabilityString>) {
        earnings_recycle.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        earnings_recycle.layoutManager = layoutManager
        earnings_recycle.adapter = EarningsAdapter(coinList)
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