package otus.homework.customview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private var graphView: GraphView? = null
    private var titleTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val purchaseList = getPayload(this)

        if (savedInstanceState == null) {
            val piecesOfCake = mapPurchases(purchaseList)
            val pointList = mapPurchasesToPoints(purchaseList)
            findViewById<PieChartView>(R.id.vPieChart).apply {
                setPieces(piecesOfCake)
                onSectorClickListener = { category ->
                    pointList[category]?.let { graphView?.setPoints(it) }
                    titleTv?.text = category
                }
            }
            graphView = findViewById(R.id.vGraph)
            titleTv = findViewById(R.id.tvCategory)
        }
    }

    private fun mapPurchasesToPoints(payments: List<Purchase>?) =
        mutableMapOf<String, List<Point>>().apply {
            payments?.groupBy { it.category }?.onEach { entry ->
                put(
                    entry.key,
                    entry.value.map { Point(it.time.toFloat(), it.amount.toFloat()) }
                )
            }
        }

    private fun mapPurchases(payments: List<Purchase>?): SortedMap<String, Float> =
        sortedMapOf<String, Float>().apply {
            payments?.groupBy { it.category }?.onEach { entry ->
                put(
                    entry.key, entry.value.sumOf { it.amount }.toFloat()
                )
            }
        }

}