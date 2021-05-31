package id.capstone.hijoe.data.remote.response


import com.google.gson.annotations.SerializedName

data class PlantResponse(
        @SerializedName("id")
        var id: String = "",
        @SerializedName("Name")
        var name: String = "",
        @SerializedName("Treatment")
        var treatment: String = "",
        @SerializedName("Desease")
        var desease: String = "",
        @SerializedName("Description")
        var description: String = ""
)