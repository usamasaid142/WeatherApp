package com.osama.domain.usecase

import com.osama.domain.repo.CurrentWeatherRepo

class GetCurrentWeather(private val currentWeatherRepo: CurrentWeatherRepo) {
    suspend operator fun invoke(
        city:String,
        appId:String
    )=currentWeatherRepo.getCurrentWeather(  city,
        appId)
}