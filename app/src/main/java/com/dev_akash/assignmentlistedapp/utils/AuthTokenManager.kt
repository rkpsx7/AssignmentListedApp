package com.dev_akash.assignmentlistedapp.utils

import android.content.Context
import com.dev_akash.assignmentlistedapp.utils.Constants.AUTH_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthTokenManager @Inject constructor() {

    fun saveToken(token: String) {
        SharedPrefs.setStringParam(AUTH_TOKEN, token)
    }

    fun getToken(): String? {
        return SharedPrefs.getStringParam(AUTH_TOKEN, null)
    }
}