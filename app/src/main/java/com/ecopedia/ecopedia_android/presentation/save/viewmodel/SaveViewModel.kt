package com.ecopedia.ecopedia_android.presentation.save.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.Bitmap
import com.ecopedia.ecopedia_android.data.datamodel.HomeDataResult
import com.ecopedia.ecopedia_android.data.repository.EncyclopediaRepository
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.CameraResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

sealed class SaveResult {
    data class Success(val data: Any) : SaveResult()
    data class Error(val message: String?) : SaveResult()
}

@HiltViewModel
class SaveViewModel @Inject constructor(private val encyclopediaRepository: EncyclopediaRepository) :
    ViewModel() {
    private val _saveResult = MutableStateFlow<SaveResult?>(null)
    val saveResult: StateFlow<SaveResult?> = _saveResult.asStateFlow()


    fun onSave(bitmap: Bitmap) {
        viewModelScope.launch {
            try {
                val result = encyclopediaRepository.creatureSave(bitmap, "37.3595704", "127.105399").firstOrNull()
                if (result == true) {
                    _saveResult.value = SaveResult.Success(bitmap)
                } else {
                    _saveResult.value = SaveResult.Error("error")
                }
            } catch (e: Exception) {
                Timber.e(e)
                _saveResult.value = SaveResult.Error(e.message)
            }
        }
    }
}