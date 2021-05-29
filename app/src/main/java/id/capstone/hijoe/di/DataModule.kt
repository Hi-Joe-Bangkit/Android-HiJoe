package id.capstone.hijoe.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.capstone.hijoe.data.local.Session
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson =
            GsonBuilder().create()

    @Singleton
    @Provides
    fun provideSession(@ApplicationContext context: Context) : Session {
        return Session(context)
    }
}