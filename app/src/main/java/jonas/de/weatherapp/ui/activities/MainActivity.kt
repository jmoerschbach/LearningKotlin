package jonas.de.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import jonas.de.weatherapp.ui.adapters.ForecastListAdapter
import jonas.de.weatherapp.R
import jonas.de.weatherapp.data.Request
import jonas.de.weatherapp.domain.commands.RequestForecastCommand
import jonas.de.weatherapp.domain.model.Forecast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this)
Log.e("Jonas bla", "hier sind wir")
        doAsync {
            val result = RequestForecastCommand(30453).execute()
 //           Log.e("Weather app", result.size.toString())
            uiThread {
                val adapter = ForecastListAdapter(result, { toast(it.description) })
                forecastList.adapter = adapter
            }
        }
    }
}
