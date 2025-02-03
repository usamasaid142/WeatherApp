package com.osama.domain.repo

import com.osama.domain.model.GetCurrentWeatherResponse

interface CurrentWeatherRepo {
    fun getCurrentWeather():GetCurrentWeatherResponse
}