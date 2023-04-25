package com.dev_akash.assignmentlistedapp.core

import android.app.Application
import com.dev_akash.assignmentlistedapp.utils.AuthTokenManager
import com.dev_akash.assignmentlistedapp.utils.SharedPrefs
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationKClass : Application() {

    companion object {
        @JvmStatic
        lateinit var INSTANCE: ApplicationKClass
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        SharedPrefs.loadAppPrefs(this)
        mockSavingTokenToPrefs()
    }

    /**
     * Assuming this token is saved after successful login in some activity or fragment.
     */
    @Inject
    lateinit var tokenManager: AuthTokenManager
    private fun mockSavingTokenToPrefs() {
        tokenManager.saveToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
    }
}