package id.capstone.hijoe.data.repository

import id.capstone.hijoe.data.remote.mapper.PlantDiseaseMapper
import id.capstone.hijoe.data.remote.source.AppRemoteDataSource
import id.capstone.hijoe.data.vo.Result
import id.capstone.hijoe.domain.model.Plant
import id.capstone.hijoe.domain.repository.PlantDiseaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlantDiseaseRepositoryImpl
@Inject constructor(
        private val remoteDataSource: AppRemoteDataSource,
        private val networkMapper: PlantDiseaseMapper
) : PlantDiseaseRepository {

    override suspend fun identifyDisease(idDisease: Int): Flow<Result<Plant>> = flow {
        when(val source = remoteDataSource.identifyDisease(idDisease)) {
            is Result.Success -> {
                val plant = networkMapper.mapFromEntity(source.data)
                emit(Result.Success(plant))
            }
            is Result.Error -> {
                emit(Result.Error(source.cause, source.exception))
            }
        }
    }
}