package com.joseph.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Utils {
    fun currentCity(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("city","Nairobi")
    }
}