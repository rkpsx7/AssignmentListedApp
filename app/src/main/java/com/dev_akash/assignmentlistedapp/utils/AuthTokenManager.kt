package com.dev_akash.assignmentlistedapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.dev_akash.assignmentlistedapp.utils.Constants.AUTH_TOKEN
import com.dev_akash.assignmentlistedapp.utils.Constants.PREFS_TOKEN_FILE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthTokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(AUTH_TOKEN, null)
    }
}