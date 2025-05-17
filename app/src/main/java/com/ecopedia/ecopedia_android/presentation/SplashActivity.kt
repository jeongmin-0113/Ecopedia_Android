package com.ecopedia.ecopedia_android.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.presentation.signin.ui.SignInActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            delay(2000)
            val intent = Intent(this@SplashActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}