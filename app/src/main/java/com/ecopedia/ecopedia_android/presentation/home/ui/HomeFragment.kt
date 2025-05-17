package com.ecopedia.ecopedia_android.presentation.home.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.composeView.setContent {
            HomeScreen(
                onCameraClick = { launchCamera() }
            )
        }
    }

    private fun launchCamera() {
        val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(newIntent)
    }

}