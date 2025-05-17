package com.ecopedia.ecopedia_android.presentation.signin.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.SignInScreen
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.SignInViewModel
import com.ecopedia.ecopedia_android.presentation.signup.ui.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : ComponentActivity() {
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginState = signInViewModel.loginState.collectAsState().value
            SignInScreen(
                onClickSignUpButton = { onClickSignUpButton() },
                onLogin = { nickname, password ->
                    signInViewModel.login(nickname, password)
                },
                loginState = loginState
            )
        }
    }

    private fun onClickSignUpButton() {
        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(intent)
    }
}