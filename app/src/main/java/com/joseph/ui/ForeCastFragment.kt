package com.joseph.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joseph.R

class ForeCastFragment : Fragment() {
    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString("city")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       //
        return inflater.inflate(R.layout.fragment_fore_cast, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(city: String) =
            ForeCastFragment().apply {
                arguments = Bundle().apply {
                    putString("city", city)
                }
            }
    }
}