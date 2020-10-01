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

class ForeCastFragment : Fragment() {
    lateinit var binding: FragmentForeCastBinding
    lateinit var forecastViewModel: ForecastViewModel
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
           binding.temperature.text="${it.current.temperature}\u00B0"
        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}