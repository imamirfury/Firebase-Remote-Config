package com.fury.firebaseremoteconfig

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

fun Any.printLog(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

object RemoteConfig {

    private val tag = RemoteConfig::class.java.simpleName
    private const val buttonText = "buttonText"
    private const val buttonColor = "buttonColor"


    private val defaults: HashMap<String, Any> =
        hashMapOf(buttonText to "Hello World", buttonColor to "#0091FF")


    @SuppressLint("StaticFieldLeak")
    private lateinit var remoteConfig: FirebaseRemoteConfig


    fun initConfig() {
        remoteConfig = buildFirebaseConfig()
    }

    private fun buildFirebaseConfig(): FirebaseRemoteConfig {
        val config = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                0
            } else {
                60 * 60
            }
        }
        config.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(defaults)
            fetchAndActivate().addOnCompleteListener {
                printLog(tag, "Remote Config Fetch Complete")
            }
        }
        return config
    }

    fun buttonText(): String = remoteConfig.getString(buttonText)

    fun buttonColor(): String = remoteConfig.getString(buttonColor)

}