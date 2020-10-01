package com.joseph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joseph.data.local.City
import com.joseph.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var navView: BottomNavigationView
    lateinit var navController: NavController
    lateinit var currentCity: City
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        navView = mainBinding.bottomNavigation
        navController = findNavController(R.id.nav_host_fragment)
        navView.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.home -> {
                navController.navigate(R.id.home_fragment)
            }
            R.id.forecast -> {
                navController.navigate(R.id.forecast_fragment)
            }
            R.id.setting -> {
                navController.navigate(R.id.setting_fragment)
            }
            R.id.help -> {
                navController.navigate(R.id.help_fragment)
            }
        }
        return true
    }
}