package com.angad.medicalapp.di

import android.content.Context
import com.angad.medicalapp.api.ApiBuilder
import com.angad.medicalapp.prefdata.MyPreferences
import com.angad.medicalapp.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

//    For injecting the preference datastore
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): MyPreferences{
        return MyPreferences(context)
    }


}