package com.ecopedia.ecopedia_android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import coil3.Bitmap
import com.ecopedia.ecopedia_android.data.repository.SampleRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val sampleRepository: SampleRepository) :
    ViewModel() {
    fun onCameraImageCaptured(bitmap: Bitmap) {

    }
}