package com.ecopedia.ecopedia_android.presentation.signup.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.databinding.ActivitySignUpBinding
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.SignInViewModel
import com.ecopedia.ecopedia_android.presentation.signup.ui.compose.SignUpScreen
import com.ecopedia.ecopedia_android.presentation.signup.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val signUpState = signUpViewModel.signUpState.collectAsState().value
            SignUpScreen(
                onClickGoBackButton = { onClickGoBackButton() },
                onSignUp = { nickname, password ->
                    signUpViewModel.signup(nickname, password)
                },
                signUpState = signUpState
            )
        }
    }

    private fun onClickGoBackButton() {
        finish()
    }
}