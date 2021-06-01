package id.capstone.hijoe.data.remote.response


import com.google.gson.annotations.SerializedName

data class PlantResponse(
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("Name")
        var name: String = "",
        @SerializedName("Treatment")
        var treatment: String = "",
        @SerializedName("Disease")
        var disease: String = "",
        @SerializedName("Description")
        var description: String = ""
)