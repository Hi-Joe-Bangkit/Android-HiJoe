package id.capstone.hijoe.data.remote

import id.capstone.hijoe.data.remote.response.PlantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{id}")
    suspend fun identifyDisease(@Path("id") id: Int) : PlantResponse
}