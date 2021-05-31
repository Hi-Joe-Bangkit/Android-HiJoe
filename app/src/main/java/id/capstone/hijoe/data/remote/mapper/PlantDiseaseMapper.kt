package id.capstone.hijoe.data.remote.mapper

import id.capstone.hijoe.abstraction.BaseMapper
import id.capstone.hijoe.data.remote.response.PlantResponse
import id.capstone.hijoe.domain.model.Plant
import javax.inject.Inject

class PlantDiseaseMapper
@Inject constructor() : BaseMapper<PlantResponse, Plant> {
    override fun mapFromEntity(entity: PlantResponse): Plant {
        return Plant(
                id = entity.id.toInt(),
                plant = entity.name,
                disease = entity.desease,
                desc = entity.description,
                solution = entity.treatment
        )
    }

    override fun mapToEntity(domainModel: Plant): PlantResponse {
        return PlantResponse()
    }
}