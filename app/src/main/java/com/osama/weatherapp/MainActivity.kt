package com.osama.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.osama.weatherapp.feature.ui.screens.WeatherPage
import com.osama.weatherapp.feature.ui.theme.WeatherAppTheme
import com.osama.weatherapp.feature.ui.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: WeatherViewModel by viewModels()
    private var name=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                       viewModel.getCurrentWeather("Egypt","f4c2c90d4744674ea08953b5c8e5f6be")
                      lifecycleScope.launch {
                          viewModel.weather.collect{
                              Log.e("data ", "weather is:${it} ", )
                              if (it != null) {
                                  name= it.name.toString()
                                  Log.e("data ", "weather is:${name} ", )
                              }
                          }
                      }

                    WeatherPage(viewModel)
                }
            }
        }
    }
}
