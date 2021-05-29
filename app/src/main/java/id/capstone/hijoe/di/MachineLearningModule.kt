package id.capstone.hijoe.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.capstone.hijoe.data.ml.ImageClassification
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MachineLearningModule {

    @Singleton
    @Provides
    fun provideImageClassification(@ApplicationContext context: Context): ImageClassification {
        return ImageClassification(context)
    }
}