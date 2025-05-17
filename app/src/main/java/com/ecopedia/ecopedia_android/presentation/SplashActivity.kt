package com.ecopedia.ecopedia_android.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.data.source.local.TokenManager
import com.ecopedia.ecopedia_android.presentation.signin.ui.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1000)

            val token = tokenManager.getAccessToken().firstOrNull()
            val intent = if (token != null) {
                Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                Intent(this@SplashActivity, SignInActivity::class.java)
            }

            startActivity(intent)
            finish() // SplashActivity 종료
        }
    }
}