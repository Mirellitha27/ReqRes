package com.iwebsapp.reqres.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class UserSession (context: Context){
    private var sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setToken(token: String?) {
        val editor = sharedPref.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun setEmail(email: String) {
        val editor = sharedPref.edit()
        editor.putString("email", email)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString("token", "")
    }

    fun getEmial(): String? {
        return sharedPref.getString("email", "")
    }

    fun logout() {
        setToken(null)
    }
}