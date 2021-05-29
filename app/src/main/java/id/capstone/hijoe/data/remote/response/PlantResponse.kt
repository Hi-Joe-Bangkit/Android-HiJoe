package id.capstone.hijoe.data.remote.response

// TODO: 29/05/2021 add @SerializedName and adjust data
data class PlantResponse(
        var id: Int = -1,
        var plant: String = "",
        var disease: String = "",
        var desc: String = "",
        var solution: String = "",
        var timestamp: Long = 0
)
