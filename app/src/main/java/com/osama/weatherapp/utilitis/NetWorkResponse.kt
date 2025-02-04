package com.osama.weatherapp.utilitis

sealed class NetWorkResponse<out T>{

    data class Success<T>(val data:T):NetWorkResponse<T>()
    data class Error(val message:String):NetWorkResponse<Nothing>()
    object Loading:NetWorkResponse<Nothing>()

}
