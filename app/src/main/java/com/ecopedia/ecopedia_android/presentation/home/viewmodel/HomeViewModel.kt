package com.ecopedia.ecopedia_android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.ecopedia.ecopedia_android.data.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val sampleRepository: SampleRepository) :
    ViewModel() {

}