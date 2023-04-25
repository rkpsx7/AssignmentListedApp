package com.dev_akash.assignmentlistedapp.core

import android.app.Application
import com.dev_akash.assignmentlistedapp.utils.AuthTokenManager
import com.dev_akash.assignmentlistedapp.utils.SharedPrefs
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationKClass : Application() {

    @Inject
    lateinit var tokenManager: AuthTokenManager
    override fun onCreate() {
        super.onCreate()
        SharedPrefs.loadAppPrefs(this)
        mockSavingTokenToPrefs()
    }

    private fun mockSavingTokenToPrefs() {
        tokenManager.saveToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
    }
}