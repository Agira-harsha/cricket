package com.agria.corporatecricket.SessionManager

import android.content.Context
import com.agria.corporatecricket.Dtos.LogInResponse

object SessionManager {

    private const val PREF_NAME = "session_data"


    fun storeLoginResponse(context: Context, loginResponse: LogInResponse) {

        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        

        editor.putInt("userId", loginResponse.userId.toInt())
        editor.putString("userName", loginResponse.userName)
        editor.putString("email", loginResponse.email)
        editor.putString("token", loginResponse.token)

        editor.apply()
    }

    fun getUserId(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        
        return sharedPreferences.getInt("userId", -1)
    }
    fun getToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }
    fun clearSession(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
