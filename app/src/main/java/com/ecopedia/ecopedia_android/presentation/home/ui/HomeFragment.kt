package com.ecopedia.ecopedia_android.presentation.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentHomeBinding
import com.ecopedia.ecopedia_android.presentation.home.ui.compose.HomeScreen
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            HomeScreen(
                onCameraClick = { /* TODO: 사진찍기 동작 */ }
            )
        }
    }
}