package jonas.de.weatherapp.data.server


import android.graphics.ColorSpace
import jonas.de.weatherapp.data.Forecast
import jonas.de.weatherapp.data.ForecastResult
import jonas.de.weatherapp.data.Weather
import jonas.de.weatherapp.data.db.CityForecast
import jonas.de.weatherapp.data.db.DayForecast
import jonas.de.weatherapp.domain.model.Forecast as ModelForecast
import jonas.de.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ServerDataMapper {




    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }


    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }
}