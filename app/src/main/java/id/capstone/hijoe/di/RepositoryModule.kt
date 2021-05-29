package id.capstone.hijoe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.capstone.hijoe.data.repository.PlantDiseaseRepositoryImpl
import id.capstone.hijoe.domain.repository.PlantDiseaseRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPlantDiseaseRepository(plantDiseaseRepository: PlantDiseaseRepositoryImpl): PlantDiseaseRepository
}