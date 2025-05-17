package com.ecopedia.ecopedia_android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.Bitmap
import com.ecopedia.ecopedia_android.data.datamodel.HomeDataResult
import com.ecopedia.ecopedia_android.data.repository.EncyclopediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class CameraResult {
    data class Success(val data: Any) : CameraResult()
    data class Error(val message: String?) : CameraResult()
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val encyclopediaRepository: EncyclopediaRepository) :
    ViewModel() {

    private val _homeData = MutableStateFlow<HomeDataResult?>(null)
    val homeData: StateFlow<HomeDataResult?> = _homeData.asStateFlow()

    private val _cameraResult = MutableStateFlow<CameraResult?>(null)
    val cameraResult: StateFlow<CameraResult?> = _cameraResult.asStateFlow()

    init {
        getHomeData()
    }

    fun getHomeData() {
        viewModelScope.launch {
            try {
                encyclopediaRepository.getHomeData().collect {
                    _homeData.value = it
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun onCameraImageCaptured(bitmap: Bitmap) {
        viewModelScope.launch {
            try {
                val result = encyclopediaRepository.creatureValidation(bitmap, "37.3595704", "127.105399").firstOrNull()
                if (result == true) {
                    _cameraResult.value = CameraResult.Success(bitmap)
                } else {
                    _cameraResult.value = CameraResult.Error("error")
                }
            } catch (e: Exception) {
                Timber.e(e)
                _cameraResult.value = CameraResult.Error(e.message)
            }
        }
    }
}