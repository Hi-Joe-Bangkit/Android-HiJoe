package id.capstone.hijoe.data.remote

import id.capstone.hijoe.data.remote.response.PlantResponse
import retrofit2.http.POST

interface ApiService {

    @POST("")
    suspend fun identifyDisease(id: Int) : PlantResponse
}