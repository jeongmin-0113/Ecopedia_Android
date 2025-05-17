package com.ecopedia.ecopedia_android.presentation.signin.viewmodel

import androidx.lifecycle.ViewModel
import com.ecopedia.ecopedia_android.data.repository.SampleRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val sampleRepository: SampleRepository) :
    ViewModel() {

}