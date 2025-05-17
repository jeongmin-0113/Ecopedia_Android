package com.ecopedia.ecopedia_android.presentation

import androidx.lifecycle.ViewModel
import coil3.Bitmap
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel : ViewModel() {
    var bitmap: Bitmap? = null
}