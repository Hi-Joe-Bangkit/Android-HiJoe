package id.capstone.hijoe.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantDisease(
        var id: Int = -1,
        var plant: String = "",
        var disease: String = "",
        var desc: String = "",
        var solution: String = "",
        var accuracy: Float = 0.0f
) : Parcelable
