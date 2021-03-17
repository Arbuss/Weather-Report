package com.example.weatherreport.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherreport.Const
import com.example.weatherreport.R
import com.example.weatherreport.data.weather.Weather
import kotlinx.android.synthetic.main.dialog_weather.*
import moxy.MvpAppCompatDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class WeatherDialog() : MvpAppCompatDialogFragment() {

    constructor(weather: Weather) : this() {
        arguments = Bundle().also {
            it.putSerializable(Const.WeatherDialog.WEATHER_KEY, weather)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.dialog_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillFields()
        bindCloseButton()
    }

    private fun fillFields() {
        (arguments?.getSerializable(Const.WeatherDialog.WEATHER_KEY) as? Weather)?.let {
            city.text = it.cityName
            date.text =
                SimpleDateFormat(Const.WeatherDialog.DATE_FORMAT, Locale.getDefault()).format(
                    Date(it.date)
                )
            temperature.text = it.temperature.toString()
            clouds.text = it.clouds.toString()
            humidity.text = it.humidity.toString()
        }
    }

    private fun bindCloseButton() {
        close.setOnClickListener { dismiss() }
    }
}