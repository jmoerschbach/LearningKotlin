package jonas.de.weatherapp.domain.datasource

import jonas.de.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long) : ForecastList?
}