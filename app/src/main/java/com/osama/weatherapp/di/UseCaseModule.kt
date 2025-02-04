package com.osama.weatherapp.di

import com.osama.domain.repo.CurrentWeatherRepo
import com.osama.domain.usecase.GetCurrentWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(currentWeatherRepo: CurrentWeatherRepo): GetCurrentWeather {
        return GetCurrentWeather(currentWeatherRepo)
    }
}