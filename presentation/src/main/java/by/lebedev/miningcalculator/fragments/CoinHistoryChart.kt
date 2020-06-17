package by.lebedev.miningcalculator.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.lebedev.domain.entities.Data
import by.lebedev.domain.repository.CoinTempData
import by.lebedev.domain.usecase.GetHistoryRatesUseCaseImpl
import by.lebedev.miningcalculator.R
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.earnings_layout.view.*
import kotlinx.android.synthetic.main.linechart_layout.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class CoinHistoryChart : Fragment() {
    private val compositeDisposable = CompositeDisposable()
    private val nf = NumberFormat.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.linechart_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val disposable = GetHistoryRatesUseCaseImpl().fetch(
            CoinTempData.instance.coinChartName,
            CoinTempData.instance.coinTimeFrame
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.size != 0) {
                    setupLineChart(it, CoinTempData.instance.coinTimeFrame)
                } else {
                    lineChart.setNoDataText("Chart not available")
                }
            }, {
                Log.e(TAG, it.localizedMessage)
                lineChart.setNoDataText("Chart not available")
            })

        compositeDisposable.add(disposable)

    }

    private fun setupLineChart(result: List<Data>, timeFrame: String) {

        lineChart.setBackgroundColor(Color.WHITE)

        lineChart.isHighlightPerTapEnabled = true
        lineChart.isHighlightPerDragEnabled = true

        // disable description text
        lineChart.description.isEnabled = false;

        // enable touch gestures
        lineChart.setTouchEnabled(true)
        lineChart.setDrawGridBackground(false)

        // enable scaling and dragging
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(true)

        // force pinch zoom along both axis
        lineChart.setPinchZoom(true)

        val xAxis = lineChart.xAxis

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 10f
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = Color.rgb(230, 133, 22)
        xAxis.setCenterAxisLabels(true)
        xAxis.granularity = 5f

        xAxis.valueFormatter = object : IAxisValueFormatter {


            var mFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("yyyy-MM", resources.configuration.locales.get(0))
            } else {
                SimpleDateFormat("yyyy-MM", resources.configuration.locale)
            }

            override fun getFormattedValue(value: Float, axis: AxisBase?): String {

                when (timeFrame) {
                    "m1" -> {
                        mFormat = SimpleDateFormat("hh:mm", resources.configuration.locale)
                    }
                    "m5" -> {
                        mFormat = SimpleDateFormat("hh:mm", resources.configuration.locale)
                    }
                    "m15" -> {
                        mFormat = SimpleDateFormat("MM-dd", resources.configuration.locale)
                    }
                    "m30" -> {
                        mFormat = SimpleDateFormat("MM-dd", resources.configuration.locale)
                    }
                    "h1" -> {
                        mFormat = SimpleDateFormat("MM-dd", resources.configuration.locale)
                    }
                    "h6" -> {
                        mFormat = SimpleDateFormat("yyyy-MM", resources.configuration.locale)
                    }
                    "h12" -> {
                        mFormat = SimpleDateFormat("yyyy-MM", resources.configuration.locale)
                    }
                    "d1" -> {
                        mFormat = SimpleDateFormat("yyyy-MM", resources.configuration.locale)
                    }

                }

                return mFormat.format(value.toLong())

            }
        }

        val leftAxis = lineChart.getAxisLeft()
        leftAxis.valueFormatter = object : IAxisValueFormatter {

            override fun getFormattedValue(value: Float, axis: AxisBase?): String {
                nf.maximumFractionDigits = 2
                return nf.format(value).toString()

            }
        }

        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(true);
        leftAxis.isGranularityEnabled = true;
        leftAxis.yOffset = 2f
        leftAxis.textColor = Color.rgb(230, 133, 22);

        val rightAxis = lineChart.axisRight;
        rightAxis.isEnabled = false;

        val values = ArrayList<Entry>()

        for (i in result.indices) {
            values.add(Entry(result.get(i).time.toFloat(), result.get(i).priceUsd.toFloat()))
        }

        // create a dataset and give it a type
        val set = LineDataSet(values, "chart")
        set.mode = LineDataSet.Mode.CUBIC_BEZIER;
        set.cubicIntensity = 0.2f
        set.setDrawFilled(true);
        set.setDrawCircles(false);
        set.lineWidth = 1.8f;
        set.fillColor = R.color.darkBlue
        set.setDrawCircleHole(false)
        set.isHighlightEnabled = true
        set.setColor(R.color.darkBlue, 100)

        // create a data object with the data sets
        val data = LineData(set);
        data.setDrawValues(false)

        // set data
        lineChart.data = data
        lineChart.animateXY(1000, 1000)
        lineChart.invalidate()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}