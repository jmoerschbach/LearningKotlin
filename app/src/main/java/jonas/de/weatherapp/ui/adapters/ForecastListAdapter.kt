package jonas.de.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jonas.de.weatherapp.R
import jonas.de.weatherapp.domain.model.Forecast
import jonas.de.weatherapp.domain.model.ForecastList
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.find

class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(override val containerView: View,
                     private val itemClick: (Forecast) -> Unit) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
                itemView.dateText.text = date
                itemView.descriptionText.text = description
                itemView.maxTemperature.text = "$high°"
                itemView.minTemperature.text = "$low°"
                itemView.setOnClickListener { itemClick(this) }

            }
        }
    }
}