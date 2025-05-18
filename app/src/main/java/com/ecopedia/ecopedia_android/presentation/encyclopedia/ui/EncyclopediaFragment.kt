package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentEncyclopediaBinding
import com.ecopedia.ecopedia_android.presentation.MainViewModel
import com.ecopedia.ecopedia_android.presentation.encyclopedia.viewmodel.EncyclopediaViewModel
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.CameraResult
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.HomeViewModel
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.SignInViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class EncyclopediaFragment :
    BaseFragment<FragmentEncyclopediaBinding>(
        FragmentEncyclopediaBinding::bind,
        R.layout.fragment_encyclopedia
    ) {


    private val tabInfo: ArrayList<String> = arrayListOf("전체", "식물", "동물", "곤충")
    private val homeViewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val encyclopediaAdapter = EncyclopediaVPAdapter(this)
        setupCameraLauncher()
        observeCameraResult()

        binding.encyclopediaBtn.setOnClickListener {
            launchCamera()
        }
        binding.encyclopediaContentVp.apply {
            adapter = encyclopediaAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(binding.encyclopediaTabTl, binding.encyclopediaContentVp) {
                tab, position ->
            tab.text = tabInfo[position] // 탭뷰의 아이템 이름 설정
        }.attach() // 탭뷰와 vp 연결

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

    private fun observeCameraResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.cameraResult.collect { result ->
                when (result) {
                    is CameraResult.Success -> {
                        mainViewModel.bitmap = result.data as Bitmap
                        findNavController().navigate(R.id.action_encyclopediaFragment_to_saveCreatureFragment)
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

    private fun launchCamera() {
        val newIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(newIntent)
    }
}