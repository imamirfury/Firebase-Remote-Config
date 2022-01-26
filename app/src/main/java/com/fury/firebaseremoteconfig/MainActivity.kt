package com.fury.firebaseremoteconfig

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fury.firebaseremoteconfig.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.apply {
            text = RemoteConfig.buttonText()
            setBackgroundColor(Color.parseColor(RemoteConfig.buttonColor()))
        }
    }
}