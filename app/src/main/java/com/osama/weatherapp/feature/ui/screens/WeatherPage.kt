package com.osama.weatherapp.feature.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osama.domain.model.GetCurrentWeatherResponse
import com.osama.weatherapp.utilitis.DataBinding
import com.osama.weatherapp.feature.ui.viewModels.WeatherViewModel
import com.osama.weatherapp.utilitis.NetWorkResponse


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    var city by remember {
        mutableStateOf("")
    }
    val weatherState by viewModel.weather.collectAsState()
    Column (
       modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            OutlinedTextField(
                modifier=Modifier.weight(1f),
                value = city,
                onValueChange = {
                city = it
            },
                label = {
                    Text(text = "search for any Location")
                }
            )
            IconButton(onClick = { viewModel.getCurrentWeather(city,"f4c2c90d4744674ea08953b5c8e5f6be") }) {
               Icon(imageVector = Icons.Default.Search, contentDescription ="search for any Location" )
            }
        }
        when (val state = weatherState) {
            is NetWorkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetWorkResponse.Success -> {
                val weather = state.data
                if (weather != null) {
                    WeatherDetails(weather)
                }
            }
            is NetWorkResponse.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
    }

}
@Composable
fun WeatherDetails(data :GetCurrentWeatherResponse){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ){
          Icon(imageVector = Icons.Default.LocationOn, contentDescription ="Location", modifier = Modifier.size(30.dp))
            data.name?.let { Text(text = it, fontSize = 20.sp) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CreateText(text = "${data.main?.tempMax?.let { DataBinding.kelvinToCelsius(it) }} ° C", color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        WeatherIcon(data.weather?.get(0)?.icon.toString())
        data.weather?.get(0)?.let { it.main?.let { it1 -> CreateText(text = it1, color = Color.Gray) } }
        Spacer(modifier = Modifier.height(16.dp))
        Card {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal("Humidity",data.main?.humidity.toString())
                    WeatherKeyVal("FeelsLike",data.main?.feelsLike.toString())
                }
            }
        }
    }
}
@Composable
fun WeatherIcon(icon:String) {
    Image(
        painter = painterResource(id = DataBinding.getWeatherIconResource(icon)),
        contentDescription = "Weather Icon",
        Modifier.size(50.dp)
    )
}

@Composable
fun CreateText(text:String,color:Color){
    Text(text = text,
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color=color
    )
}

@Composable
fun WeatherKeyVal(key : String, value : String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = key, fontWeight = FontWeight.SemiBold, color = Color.Gray)
    }
}