package com.osama.data.repo

import com.osama.data.remote.ApiServices
import com.osama.domain.model.GetCurrentWeatherResponse
import com.osama.domain.repo.CurrentWeatherRepo

class CurrentWeatherImpl(private val apiServices: ApiServices):CurrentWeatherRepo {
    override fun getCurrentWeather(): GetCurrentWeatherResponse = apiServices.getCurrentWeather()

}