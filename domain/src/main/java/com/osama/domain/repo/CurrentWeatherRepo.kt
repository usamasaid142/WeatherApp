package com.osama.domain.repo

import com.osama.domain.model.GetCurrentWeatherResponse
import retrofit2.Response

interface CurrentWeatherRepo {
     suspend fun getCurrentWeather(
         city:String,
         appId:String
     ):Response<GetCurrentWeatherResponse>
}