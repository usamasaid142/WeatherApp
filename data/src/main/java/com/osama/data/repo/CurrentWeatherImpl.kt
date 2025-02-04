package com.osama.data.repo

import com.osama.data.remote.ApiServices
import com.osama.domain.model.GetCurrentWeatherResponse
import com.osama.domain.repo.CurrentWeatherRepo
import retrofit2.Response

class CurrentWeatherImpl(private val apiServices: ApiServices) : CurrentWeatherRepo {
    override suspend fun getCurrentWeather(
        city: String,
        appId: String
    ): Response<GetCurrentWeatherResponse> {
        return apiServices.getCurrentWeather(city, appId)
    }


}