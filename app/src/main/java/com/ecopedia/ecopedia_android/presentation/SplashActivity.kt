package com.ecopedia.ecopedia_android.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.data.source.local.TokenManager
import com.ecopedia.ecopedia_android.presentation.signin.ui.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            delay(2000)
            val token = tokenManager.getAccessToken().firstOrNull()
            Log.d("TEST", "token ${token}")
            val intent = if (token != null) Intent(this@SplashActivity, MainActivity::class.java)
            else Intent(this@SplashActivity, SignInActivity::class.java)

            startActivity(intent)
        }
    }
}