package com.ecopedia.ecopedia_android.presentation.signin.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.databinding.ActivityMainBinding
import com.ecopedia.ecopedia_android.databinding.ActivitySignInBinding
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.SignInScreen
import timber.log.Timber

class SignInActivity : ComponentActivity() {
    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TEST", "컴포즈 들어가기 전")

        super.onCreate(savedInstanceState)
        setContent {
            SignInScreen()
            Log.d("TEST", "컴포즈 끝")
        }
    }
}