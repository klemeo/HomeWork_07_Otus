package otus.homework.customview

import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class PieChartSavedState : View.BaseSavedState {
    private var list: List<SavedSector>? = null

    fun getSectorList() =
        list?.map {
            Sector(
                it.id,
                it.startAngle,
                it.sweepAngle,
                Paint().apply { color = it.color })
        }

    constructor(list: List<Sector>, superState: Parcelable?) : super(superState) {
        this.list = list.map { SavedSector(it.id, it.startAngle, it.sweepAngle, it.paint.color) }
    }

    constructor(source: Parcel) : super(source) {
        list?.let { source.readList(it, SavedSector::class.java.classLoader) }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeList(list)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PieChartSavedState> =
            object : Parcelable.Creator<PieChartSavedState> {
                override fun createFromParcel(source: Parcel): PieChartSavedState {
                    return PieChartSavedState(source)
                }

                override fun newArray(size: Int): Array<PieChartSavedState> {
                    return newArray(size)
                }
            }
    }
}