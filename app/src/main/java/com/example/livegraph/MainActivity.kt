package com.example.livegraph

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var textView: TextView

    private val RANDOM: Random = Random()
    private var series: LineGraphSeries<DataPoint>? = null
    private var lastX = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //the textView as a timer
        textView = findViewById(R.id.textView)
        viewModel = MainViewModel()

        val graph = findViewById<View>(R.id.graph) as GraphView

        // data
        series = LineGraphSeries()
        graph.addSeries(series)

        //viewPort is the place to customize the graph
        val viewport = graph.viewport
        viewport.isYAxisBoundsManual = true
        viewport.setMinY(0.0)
        viewport.setMaxY(10.0)

        viewport.isXAxisBoundsManual = true
        viewport.setMinX(0.0)
        viewport.setMaxX(10.0)

        viewport.isScrollable = true

        //every second, when a second passes, print a new random number to the graph
        viewModel.numbers.observe(this, Observer {
            addEntry(it)
            textView.text = it.toString()
        })


    }

    private fun addEntry(num: Int) {
        // If the scroll value is true, the graph y axis is shown on right side.
        // Therefore, start with false, and after the 11th value is displayed,
        // start to scroll

        var scroll = false
        if (num > 11) scroll = true

        //print random numbers on y axis (1 to 10) and on x axis 1 step forward
        series!!.appendData(DataPoint((lastX++).toDouble(), RANDOM.nextDouble() * 10.0), scroll, 50)
    }

}