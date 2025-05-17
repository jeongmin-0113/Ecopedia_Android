package com.ecopedia.ecopedia_android.presentation.signin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentSignInBinding
import com.ecopedia.ecopedia_android.presentation.signin.ui.compose.SignInScreen
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.SignInViewModel

class HomeFragment :
    BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::bind, R.layout.fragment_sign_in) {
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            SignInScreen()
        }
    }
}