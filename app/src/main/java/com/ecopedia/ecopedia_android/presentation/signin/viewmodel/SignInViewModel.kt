package com.ecopedia.ecopedia_android.presentation.signin.viewmodel

import androidx.lifecycle.ViewModel
import com.ecopedia.ecopedia_android.data.repository.SampleRepository
import com.ecopedia.ecopedia_android.data.repository.UserRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    fun login(nickname: String, password: String) = userRepository.login(nickname, password)
    }