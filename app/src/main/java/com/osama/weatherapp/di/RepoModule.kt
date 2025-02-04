package com.osama.weatherapp.di

import com.osama.data.remote.ApiServices
import com.osama.data.repo.CurrentWeatherImpl
import com.osama.domain.repo.CurrentWeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(apiServices: ApiServices): CurrentWeatherRepo {
        return CurrentWeatherImpl(apiServices)
    }
}