package com.osama.weatherapp.feature.ui.viewModels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osama.domain.model.GetCurrentWeatherResponse
import com.osama.domain.usecase.GetCurrentWeather
import com.osama.weatherapp.utilitis.NetWorkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase:GetCurrentWeather
) : ViewModel() {

    private val _weather = MutableStateFlow<NetWorkResponse<GetCurrentWeatherResponse?>>(NetWorkResponse.Loading)
    val weather:StateFlow<NetWorkResponse<GetCurrentWeatherResponse?>> = _weather
    fun getCurrentWeather(city:String,appId:String) {
        NetWorkResponse.Loading
       viewModelScope.launch (errorHandler){
           val response=getWeatherUseCase(city,appId)
           if (response.isSuccessful){
               response.body().let {
                   _weather.value=NetWorkResponse.Success(it)
               }
           }else{
               _weather.value=NetWorkResponse.Error("Error")
           }
       }

    }

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
        _weather.value=NetWorkResponse.Error( throwable.printStackTrace().toString())
    }
}