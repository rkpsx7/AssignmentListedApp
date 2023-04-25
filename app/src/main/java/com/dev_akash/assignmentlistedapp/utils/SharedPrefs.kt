package com.dev_akash.assignmentlistedapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import com.dev_akash.assignmentlistedapp.utils.Constants.DEFAULT_INT_VALUE
import com.dev_akash.assignmentlistedapp.utils.Constants.DEFAULT_STRING_VALUE
import com.dev_akash.assignmentlistedapp.utils.Constants.PREFS_NAME

object SharedPrefs {

    private val editor: SharedPreferences.Editor
        get() = prefs.edit()

    fun setStringParam(key: String?, value: String?) {
        try {
            editor.putString(key, value).apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun clearAll() {
        editor.clear().commit()
    }

    fun getStringParam(key: String?): String? {
        return getStringParam(key, DEFAULT_STRING_VALUE)
    }

    fun getStringParam(key: String?, defVal: String?): String? {
        return prefs.getString(key, defVal)
    }

    fun getIntParam(key: String?, defaultValue: Int): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun getIntParam(key: String?): Int {
        return getIntParam(key, DEFAULT_INT_VALUE)
    }

    fun hasParam(key: String?): Boolean {
        return prefs.contains(key)
    }

    fun clearParam(key: String?) {
        prefs.edit().remove(key).apply()
    }

    private lateinit var prefs: SharedPreferences

    fun loadAppPrefs(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}