package com.ecopedia.ecopedia_android.presentation.signup.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.databinding.ActivitySignUpBinding
import com.ecopedia.ecopedia_android.presentation.signup.ui.compose.SignUpScreen

class SignUpActivity : ComponentActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen(
                onClickGoBackButton = {onClickGoBackButton()}
            )
        }
    }

    private fun onClickGoBackButton() {
        finish()
    }
}