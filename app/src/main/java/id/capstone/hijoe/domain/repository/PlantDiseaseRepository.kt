package id.capstone.hijoe.domain.repository

import id.capstone.hijoe.data.vo.Result
import id.capstone.hijoe.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantDiseaseRepository {
    suspend fun identifyDisease(idDisease: Int): Flow<Result<Plant>>
}