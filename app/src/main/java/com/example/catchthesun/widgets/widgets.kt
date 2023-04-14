package com.example.catchthesun.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catchthesun.city_model.City
import com.example.catchthesun.components.HourlyWeatherRow
import com.example.catchthesun.components.WeeklyForecastColumn
import com.example.catchthesun.ui.theme.myLightPurple
import com.example.catchthesun.utils.formatDecimals
import com.example.catchthesun.weather_model.Weather
import com.example.catchthesun.R

@Composable
fun WeatherTopInfo(weather: Weather, city: City){

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = city[0].name,
            fontSize = 22.sp, color = Color.White,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = formatDecimals(weather.current.temp) + "°",
            fontSize = 94.sp, color = Color.White,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        )

        Text(
            text = weather.current.weather[0].description,
            fontSize = 15.sp,
            color = Color.White
        )

        Text(
            text = "Od ${formatDecimals(weather.daily[0].temp.min)}° do ${
                formatDecimals(
                    weather.daily[0].temp.max
                )
            }°",
            fontSize = 15.sp,
            color = Color.White
        )
    }
}

@Composable
fun HourlyWeatherCard(weather: Weather) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp),
        color = myLightPurple.copy(alpha = 0.40f),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Prognoza Godzinowa",
                    fontSize = 15.sp,
                    color = Color.White
                )

                Row(modifier = Modifier.padding(top = 1.dp, start = 10.dp)){

                    Image(painter = painterResource(id = R.drawable.clock_icon), contentDescription = null,
                        modifier = Modifier.size(18.dp))
                }
            }
            Divider(
                modifier = Modifier
                    .width(310.dp)
                    .border(width = 1.dp, color = Color.LightGray)
            )


            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                HourlyWeatherRow(weather = weather)

            }

        }


    }
}

@Composable
fun WeeklyForecastCard(weather: Weather) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        color = myLightPurple.copy(alpha = 0.40f),
        shape = RoundedCornerShape(20.dp),

    ) {
        Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Prognoza Tygodniowa",
                    fontSize = 15.sp,
                    color = Color.White
                )
                Row(modifier = Modifier.padding(top = 1.dp, start = 10.dp)){

                    Image(painter = painterResource(id = R.drawable.calendar_icon), contentDescription = null,
                        modifier = Modifier.size(18.dp))
                }

            }
            Divider(
                modifier = Modifier
                    .width(310.dp)
                    .border(width = 1.dp, color = Color.LightGray)
            )

            WeeklyForecastColumn(weather = weather)

        }

    }
}

@Composable
fun SquareInfoCard( padding: Dp = 25.dp,
                    upperText: String,
                    mainText: String,
                    optionalText: String = "",
                    icon: Int,
                    height: Dp = 150.dp){

    Surface(
        modifier = Modifier
            .width(150.dp)
            .height(height),
        color = myLightPurple.copy(alpha = 0.40f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row(modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center){

                Text(text = upperText,
                    fontSize = 15.sp,
                    color = Color.White)

                Row(modifier = Modifier.padding(top = 1.dp, start = 5.dp)){

                    Image(painter = painterResource(id = icon), contentDescription = null,
                        modifier = Modifier.size(18.dp))
                }

            }


            Divider(
                modifier = Modifier
                    .width(125.dp)
                    .border(width = 1.dp, color = Color.LightGray)
            )

            Column(modifier = Modifier.fillMaxSize()
                .padding(top = padding), horizontalAlignment = Alignment.CenterHorizontally){

                Row(verticalAlignment = Alignment.CenterVertically){

                    Text(text = mainText,
                        color = Color.White,
                        fontSize = 38.sp)

                    Text(text = optionalText,
                    color = Color.White,
                    fontSize = 15.sp)
                }
            }

        }
    }
}

@Composable
fun SquareWindInfoCard( icon: Int,
                        mainIcon: Int,
                        upperText: String,
                        mainText: String,
                        degrees: Float){

    Surface(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
        color = myLightPurple.copy(alpha = 0.40f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row(modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center){

                Text(text = upperText,
                    fontSize = 15.sp,
                    color = Color.White)

                Row(modifier = Modifier.padding(top = 1.dp, start = 5.dp)){

                    Image(painter = painterResource(id = icon), contentDescription = null,
                        modifier = Modifier.size(18.dp))
                }

            }


            Divider(
                modifier = Modifier
                    .width(125.dp)
                    .border(width = 1.dp, color = Color.LightGray)
            )

            Column(modifier = Modifier.padding(top = 13 .dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){

                Image(painter = painterResource(id = mainIcon), contentDescription = null,
                    modifier = Modifier.rotate(degrees + 180f).size(55.dp))

                Text(text = mainText,
                    color = Color.White,
                    fontSize = 25.sp)
            }

        }
    }
}


