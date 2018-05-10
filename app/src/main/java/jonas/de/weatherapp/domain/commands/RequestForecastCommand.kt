package jonas.de.weatherapp.domain.commands

import jonas.de.weatherapp.data.ForecastRequest
import jonas.de.weatherapp.domain.mappers.ForecastDataMapper
import jonas.de.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {

   override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}