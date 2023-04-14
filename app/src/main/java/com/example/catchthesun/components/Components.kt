package com.example.catchthesun.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catchthesun.R
import com.example.catchthesun.city_model.City
import com.example.catchthesun.ui.theme.myDarkPurple
import com.example.catchthesun.ui.theme.myLightPurple
import com.example.catchthesun.utils.*
import com.example.catchthesun.weather_model.Daily
import com.example.catchthesun.weather_model.Hourly
import com.example.catchthesun.weather_model.Weather

@Composable
fun WeatherCard(weather: Weather, city: City) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(114.dp)
            .clip(shape = RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = city[0].name,
                    fontSize = 20.sp
                )
                Text(text = formatDateTime(weather.current.dt))

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Text(text = weather.current.weather[0].description)

                }

            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "22°",
                    fontSize = 35.sp
                )

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {

                    Text(text = "Od 10 do 22")

                }


            }

        }


    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit = {}){

    val valueState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(valueState.value) { valueState.value.trim().isNotBlank() }



    SearchTextField(valueState,
        placeHolder = "Search...",
        onAction = KeyboardActions {
            if(!valid) return@KeyboardActions
            onSearch(valueState.value.trim())
            valueState.value = ""
            keyboardController?.hide()




        }
    )

}


@Composable
fun SearchTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    onAction: KeyboardActions = KeyboardActions.Default
) {


    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeHolder) },
        textStyle = TextStyle(
            fontSize = 15.sp,
            color = Color.LightGray
        ),
        maxLines = 1,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search_icon"
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            cursorColor = Color.LightGray,
            textColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            trailingIconColor = Color.LightGray,
            focusedLabelColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray
        ),
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    )


}


@Composable
fun HourlyWeatherRow(weather: Weather) {

    LazyRow(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp),
        content = {
            items(weather.hourly) { weatherData ->
                HourlyWeatherDataDisplay(
                    weatherHourly = weatherData,
                    modifier = Modifier.height(95.dp)
                )
            }

        })

}

@Composable
fun HourlyWeatherDataDisplay(weatherHourly: Hourly, modifier: Modifier) {

    Column(
        modifier = modifier.padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = if (formatDateTime(weatherHourly.dt) == "00") {
                when (formatDateDay(weatherHourly.dt)) {
                    "pon." -> "Pon"
                    "wt." -> "Wt"
                    "śr." -> "Śr"
                    "czw." -> "Czw"
                    "pt." -> "Pt"
                    "sob." -> "Sob"
                    "niedz." -> "Ndz"
                    else -> ""
                }
            } else {
                formatDateTime(weatherHourly.dt)
            },
            fontSize = 18.sp,
            color = Color.White
        )

        Image(
            painter = painterResource(
                id = whichWeatherIcon(icon = weatherHourly.weather[0].icon)
            ),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )

        Text(
            text = formatDecimals(weatherHourly.temp) + "°",
            fontSize = 18.sp,
            color = Color.White

        )


    }

}


fun DrawScope.BackgroundIndicator() {

    drawLine(
        color = Color.LightGray,
        start = Offset(x = 0f, y = 9f),
        end = Offset(x = 200f, y = 9f),
        strokeWidth = 13f,
        cap = StrokeCap.Round
    )
}


fun DrawScope.ForegroundIndicator(weatherDaily: Daily, weather: Weather) {

    val diff = 200 / (formatDecimals(findHighest(weather)).toFloat() - formatDecimals(findLowest(weather)).toFloat())

    val offSetStart: Float =
            (formatDecimals(weatherDaily.temp.min).toFloat() - formatDecimals(findLowest(weather)).toFloat()) * diff

    val offSetEnd: Float =
        (formatDecimals(weatherDaily.temp.max).toFloat() - formatDecimals(weatherDaily.temp.min).toFloat()) * diff + offSetStart

    drawLine(
        color = myDarkPurple,
        start = Offset(x = offSetStart, y = 9f),
        end = Offset(x = offSetEnd, y = 9f),
        strokeWidth = 13f,
        cap = StrokeCap.Round
    )
}


@Composable
fun WeeklyForecastRow(weatherDaily: Daily, weather: Weather) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.142f)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(0.3f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(
                text = when (formatDateDay(weatherDaily.dt)) {
                    "pon." -> "Pon"
                    "wt." -> "Wt"
                    "śr." -> "Śr"
                    "czw." -> "Czw"
                    "pt." -> "Pt"
                    "sob." -> "Sob"
                    "niedz." -> "Ndz"
                    else -> ""
                },
                color = Color.White,
                fontSize = 18.sp
            )


            Image(
                painter = painterResource(
                    id = whichWeatherIcon(icon = weatherDaily.weather[0].icon)
                ),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = formatDecimals(weatherDaily.temp.min) + "°",
                color = Color.White,
                modifier = Modifier.padding(end = 20.dp)
            )

            Row(
                modifier = Modifier
                    .width(100.dp)
                    .height(7.dp)
                    .drawBehind {

                        BackgroundIndicator()
                        ForegroundIndicator(weatherDaily, weather)

                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {}

            Text(
                text = formatDecimals(weatherDaily.temp.max) + "°",
                color = Color.White
            )
        }
    }

}

@Composable
fun WeeklyForecastColumn(weather: Weather) {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        userScrollEnabled = false,
        content = {
            items(weather.daily - weather.daily[0]) { weatherData ->
                WeeklyForecastRow(
                    weatherDaily = weatherData,
                    weather = weather
                )
            }

        })

}


// clouds movie = android.resource://com.example.catchthesun/2131820544
// raindrops movie = android.resource://com.example.catchthesun/2131820545
// sun_clouds movie = android.resource://com.example.catchthesun/2131820546
// sun movie = android.resource://com.example.catchthesun/2131820547
// thunder = android.resource://com.example.catchthesun/2131820548


@Composable
fun CatchTheSunAppBottomBar(
    onFavouritesButtonClicked: () -> Unit,
    onSearchButtonClicked: () -> Unit,
) {


    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = myLightPurple
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {

            IconButton(onClick = { onSearchButtonClicked.invoke() })
            {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }

            IconButton(
                onClick = { onFavouritesButtonClicked.invoke() },
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.heart_icon),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )

            }


        }

    }

}