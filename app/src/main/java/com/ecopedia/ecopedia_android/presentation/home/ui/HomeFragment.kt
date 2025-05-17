package com.ecopedia.ecopedia_android.presentation.home.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentHomeBinding
import com.ecopedia.ecopedia_android.presentation.MainViewModel
import com.ecopedia.ecopedia_android.presentation.home.ui.compose.HomeScreen
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.CameraResult
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCameraLauncher()
        observeHomeData()
        observeCameraResult()
    }

    private fun setupCameraLauncher() {
        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        it.data?.getParcelableExtra("data", Bitmap::class.java)
                    } else {
                        it.data?.getParcelableExtra<Bitmap>("data")
                    }
                    bitmap?.let { bmp ->
                        homeViewModel.onCameraImageCaptured(bmp)
                    }
                }
            }
    }

    private fun observeHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.homeData.collect { homeData ->
                        binding.composeView.setContent {
                            HomeScreen(
                                homeData = homeData,
                                onCameraClick = { launchCamera() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun observeCameraResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.cameraResult.collect { result ->
                    when (result) {
                        is CameraResult.Success -> {
                            mainViewModel.bitmap = result.data as Bitmap
                            findNavController().navigate(R.id.action_homeFragment_to_saveCreatureFragment)
                        }
                        is CameraResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                result.message ?: "이미지 처리 중 오류가 발생했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        null -> {}
                    }
                }
            }
        }
    }

    private fun launchCamera() {
        val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(newIntent)
    }
}