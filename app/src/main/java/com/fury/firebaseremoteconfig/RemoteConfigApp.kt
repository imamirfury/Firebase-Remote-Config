package com.fury.firebaseremoteconfig

import android.app.Application

class RemoteConfigApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RemoteConfig.initConfig()

    }
}