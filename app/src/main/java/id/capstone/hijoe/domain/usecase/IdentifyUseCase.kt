package id.capstone.hijoe.domain.usecase

import id.capstone.hijoe.data.vo.Result
import id.capstone.hijoe.domain.model.Plant
import id.capstone.hijoe.domain.repository.PlantDiseaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IdentifyUseCase
@Inject constructor(private val repository: PlantDiseaseRepository) {

    suspend operator fun invoke(params: IdentifyParams) : Flow<Result<Plant>> {
        return repository.identifyDisease(params.id)
    }

    data class IdentifyParams(
            val id: Int
    )
}