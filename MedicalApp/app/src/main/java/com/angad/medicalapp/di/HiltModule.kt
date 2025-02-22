package com.angad.medicalapp.di

import com.angad.medicalapp.api.ApiBuilder
import com.angad.medicalapp.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

//    For injecting the Api Builder
    @Provides
    @Singleton
    fun provideApi(): ApiBuilder{
        return  ApiBuilder
    }

//    For injecting the Repo
    @Provides
    @Singleton
    fun provideRepo(
        apiBuilder: ApiBuilder
    ): Repo{
        return Repo(apiBuilder)
    }
}