package com.ecopedia.ecopedia_android.presentation.save.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil3.Bitmap
import coil3.imageLoader
import coil3.request.ImageRequest
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentSaveCreatureBinding
import com.ecopedia.ecopedia_android.presentation.MainViewModel
import com.ecopedia.ecopedia_android.presentation.save.viewmodel.SaveResult
import com.ecopedia.ecopedia_android.presentation.save.viewmodel.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveCreatureFragment :
    BaseFragment<FragmentSaveCreatureBinding>(
        FragmentSaveCreatureBinding::bind,
        R.layout.fragment_save_creature
    ) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val saveViewModel: SaveViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.bitmap?.let { btm ->
            binding.imageView.setImageBitmap(btm)

            binding.saveBtn.setOnClickListener {
                saveViewModel.onSave(btm)
            }
        }

        observeSaveResult()

    }

    private fun observeSaveResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                saveViewModel.saveResult.collect { result ->
                    when (result) {
                        is SaveResult.Success -> {
                            findNavController().popBackStack()
                        }

                        is SaveResult.Error -> {
                            Toast.makeText(
                                requireContext(),
                                result.message ?: "처리 중 오류가 발생했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        null -> {}
                    }
                }
            }
        }
    }
}