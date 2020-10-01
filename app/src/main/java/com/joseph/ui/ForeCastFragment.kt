package com.joseph.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joseph.data.local.Forecast
import com.joseph.databinding.FragmentForeCastBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ForeCastFragment : Fragment() {
    lateinit var binding: FragmentForeCastBinding
    lateinit var forecastViewModel: ForecastViewModel
    lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForeCastBinding.inflate(inflater, container, false)
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        forecastViewModel.getForecast().observe(viewLifecycleOwner, Observer<Forecast> {
            val current = it.weekly[0]!!.weather
            binding.city.text = current.city
            binding.condition.text = current.description
            binding.temperature.text = "${current.temperature}\u00B0"
            Picasso.get().load("http://openweathermap.org/img/wn/${current.icon}@2x.png")
                .into(binding.forecastToday)
            val date = Date()
            Log.i("date", current.date.toString())
            date.time = current.date * 1000
            val fmt = SimpleDateFormat("yyyy-MM-dd ")
            binding.date.text = "${fmt.format(date)}"
            binding.humidity.text="${current.humidity}%"
            binding.temperatureD.text="${current.temperature}°"
            binding.rain.text=current.forecast
            binding.wind.text="${current.wind}m/s"
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}