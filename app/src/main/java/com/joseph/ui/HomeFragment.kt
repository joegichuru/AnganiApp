package com.joseph.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joseph.R
import com.joseph.data.local.Weather
import com.joseph.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var homeBinding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherViewModel.getWeather.observe(viewLifecycleOwner, Observer<Weather> {
            homeBinding.city.text = it.city
            homeBinding.description.text = it.forecast
            homeBinding.longDesc.text = it.description
            homeBinding.temperature.text = "${it.temperature}\u00B0"
            homeBinding.wind.text = "${it.wind} m/s"
            Picasso.get().load("http://openweathermap.org/img/wn/${it.icon}@2x.png")
                .into(homeBinding.forecastToday)
            homeBinding.rain.text = "${it.humidity}"
            homeBinding.sun.text = "${it.cloud}"
        })
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getIcon(forecast: String): Int {
        return when (forecast.toLowerCase()) {
            "Cloudy" -> R.drawable.cloud
            "Sunny" -> R.drawable.sunshine
            else -> R.drawable.forecast
        }
    }
}