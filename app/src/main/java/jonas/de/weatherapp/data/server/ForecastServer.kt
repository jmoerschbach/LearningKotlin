package jonas.de.weatherapp.data.server

import jonas.de.weatherapp.data.db.ForecastDb
import jonas.de.weatherapp.domain.datasource.ForecastDataSource
import jonas.de.weatherapp.domain.model.ForecastList

class ForecastServer(
        private val dataMapper: ServerDataMapper = ServerDataMapper(),
        private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}