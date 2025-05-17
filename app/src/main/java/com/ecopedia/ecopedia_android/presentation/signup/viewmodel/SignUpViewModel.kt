package com.ecopedia.ecopedia_android.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.ecopedia.ecopedia_android.data.repository.SampleRepository
import com.ecopedia.ecopedia_android.data.repository.UserRepository
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    fun signup(nickname: String, password: String) = userRepository.signup(nickname, password)
}