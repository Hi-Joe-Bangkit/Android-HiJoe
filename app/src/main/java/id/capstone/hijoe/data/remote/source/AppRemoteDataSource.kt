package id.capstone.hijoe.data.remote.source

import id.capstone.hijoe.data.remote.ApiService
import id.capstone.hijoe.data.remote.response.PlantResponse
import id.capstone.hijoe.data.vo.Result
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppRemoteDataSource
@Inject constructor(private val apiService: ApiService) : StateRemoteDataSource() {

    suspend fun identifyDisease(idDisease: Int) : Result<PlantResponse> {
        return safeApiCall(Dispatchers.IO) { apiService.identifyDisease(idDisease) }
    }
}