package com.joseph.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joseph.R
import com.joseph.data.local.Forecast
import com.joseph.data.local.Weather
import com.joseph.databinding.FragmentForeCastBinding
import com.squareup.picasso.Picasso

class ForeCastFragment : Fragment() {
    lateinit var binding: FragmentForeCastBinding
    lateinit var forecastViewModel: ForecastViewModel
    lateinit var weatherViewModel:WeatherViewModel
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

        })
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherViewModel.getWeather.observe(viewLifecycleOwner, Observer<Weather> {
            binding.city.text = it.city
            binding.condition.text = it.forecast
            binding.temperature.text = "${it.temperature}\u00B0"
            Picasso.get().load("http://openweathermap.org/img/wn/${it.icon}@2x.png")
                .into(binding.forecastToday)

        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}