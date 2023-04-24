package com.dev_akash.assignmentlistedapp

import android.app.Application
import com.dev_akash.assignmentlistedapp.utils.AuthTokenManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationKClass : Application() {

    @Inject
    lateinit var tokenManager: AuthTokenManager
    override fun onCreate() {
        super.onCreate()
        mockSavingTokenToPrefs()
    }

    private fun mockSavingTokenToPrefs() {
        tokenManager.saveToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
    }
}