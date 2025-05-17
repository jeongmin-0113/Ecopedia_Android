package com.ecopedia.ecopedia_android.presentation.encyclopedia.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ecopedia.ecopedia_android.data.repository.EncyclopediaRepository
import com.ecopedia.ecopedia_android.data.repository.SampleRepository
import com.ecopedia.ecopedia_android.data.source.remote.Item
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.LoginUiState
import com.ecopedia.ecopedia_android.presentation.signup.viewmodel.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EncyclopediaViewModel @Inject constructor(
    private val encyclopediaRepository: EncyclopediaRepository
): ViewModel() {

    private val _itemState = MutableStateFlow<ItemReceiveUiState>(ItemReceiveUiState.Idle)
    val itemState: StateFlow<ItemReceiveUiState> = _itemState
    var itemList: MutableList<Item> = ArrayList<Item>()

    fun getAllItems() {
        viewModelScope.launch {
            encyclopediaRepository.getAllItems()
                .onStart { _itemState.value = ItemReceiveUiState.Loading }
                .catch { e -> _itemState.value = ItemReceiveUiState.Error(e.message ?: "아이템 가져오기 실패") }
                .collect {
                    _itemState.value = ItemReceiveUiState.Success
                    itemList = it.toMutableList()
                }
        }
    }

    fun resetState() {
        _itemState.value = ItemReceiveUiState.Idle
    }
}

sealed class ItemReceiveUiState {
    object Idle : ItemReceiveUiState()
    object Loading : ItemReceiveUiState()
    object Success : ItemReceiveUiState()
    data class Error(val message: String) : ItemReceiveUiState()
}