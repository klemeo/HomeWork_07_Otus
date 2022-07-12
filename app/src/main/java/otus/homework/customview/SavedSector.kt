package otus.homework.customview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SavedSector(
    val id: String,
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Int
) : Parcelable