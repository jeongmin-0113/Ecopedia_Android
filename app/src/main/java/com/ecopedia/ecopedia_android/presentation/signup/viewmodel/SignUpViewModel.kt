package com.ecopedia.ecopedia_android.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecopedia.ecopedia_android.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val signUpState: StateFlow<SignUpUiState> = _signUpState

    fun signup(nickname: String, password: String) {
        viewModelScope.launch {
            userRepository.signup(nickname, password)
                .onStart { _signUpState.value = SignUpUiState.Loading }
                .catch { e -> _signUpState.value = SignUpUiState.Error(e.message ?: "회원가입 실패") }
                .collect {
                    _signUpState.value = SignUpUiState.Success
                }
        }
    }

    fun resetState() {
        _signUpState.value = SignUpUiState.Idle
    }
}

sealed class SignUpUiState {
    object Idle : SignUpUiState()
    object Loading : SignUpUiState()
    object Success : SignUpUiState()
    data class Error(val message: String) : SignUpUiState()
}