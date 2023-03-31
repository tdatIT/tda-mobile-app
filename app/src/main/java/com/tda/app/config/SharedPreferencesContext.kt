package com.tda.app.config

import User
import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*


object SharedPreferencesContext {
    private const val PREFERENCE_NAME = "TDA_APP"
    private const val JWT_KEY = "JWT_BEARER"
    private const val JWT_EXPIRY_KEY = "JWT_EXPIRY"
    private const val US_NAME_KEY = "USER_FULLNAME"
    private const val US_EMAIL_KEY = "USER_EMAIL"

    private fun getSharedPreferencesContext(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun putUserInfo(context: Context, jwt: String, expiryDate: Date, user: User) {
        val editor = getSharedPreferencesContext(context).edit()
        editor.putString(JWT_KEY, jwt)
        editor.putString(JWT_EXPIRY_KEY, dateToString(expiryDate, ""))
        editor.putString(US_NAME_KEY, user.firstname + " " + user.lastname)
        editor.putString(US_EMAIL_KEY, user.email)
        editor.apply()
    }

    fun isValidJWT(context: Context): Boolean {
        val sharedPreferences = getSharedPreferencesContext(context)
        val jwt = sharedPreferences.getString(JWT_KEY, null)
        val expiry = sharedPreferences.getString(JWT_EXPIRY_KEY, null)
        if (jwt == null && expiry == null) {
            return false
        }
        //chưa xử lý
        return true
    }

    fun removeJwtAndUser(context: Context) {
        val sharedPreferences = getSharedPreferencesContext(context)
        val editor = sharedPreferences.edit()
        //
        editor.remove(JWT_KEY)
        editor.remove(JWT_EXPIRY_KEY)
        editor.remove(US_NAME_KEY)
        editor.remove(US_EMAIL_KEY)
        editor.apply()
    }

    fun dateToString(date: Date, format: String): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date)
    }

}