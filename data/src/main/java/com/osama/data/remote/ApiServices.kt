package com.osama.data.remote

import com.osama.domain.model.GetCurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String, // City name
        @Query("appid") apiKey: String, // API key
    ):Response<GetCurrentWeatherResponse>
}