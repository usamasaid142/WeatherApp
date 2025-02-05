package com.osama.weatherapp.utilitis

import com.osama.weatherapp.R
import kotlin.math.roundToInt

object DataBinding {
    fun getWeatherIconResource(iconCode: String): Int {
        return when (iconCode) {
            "01d" -> R.drawable.clear_sky
            "01n" -> R.drawable.clear_sky
            "02d" -> R.drawable.cloud
            "02n" -> R.drawable.cloud
            "03d", "03n" -> R.drawable.cloud
            "04d", "04n" -> R.drawable.cloud
            "09d", "09n" -> R.drawable.raining
            "10d" -> R.drawable.raining
            "10n" -> R.drawable.raining
            else -> R.drawable.clouds_and_sun
        }
    }

    fun kelvinToCelsius(kelvin: Double): Double {
      return (kelvin - 273.15).roundToInt().toDouble() // Rounds to the nearest integer
    }
}