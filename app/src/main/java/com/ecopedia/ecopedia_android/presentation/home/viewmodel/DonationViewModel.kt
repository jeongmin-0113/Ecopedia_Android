package com.ecopedia.ecopedia_android.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecopedia.ecopedia_android.data.datamodel.DonationStatus
import com.ecopedia.ecopedia_android.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer
import javax.inject.Inject

@HiltViewModel
class DonationViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _donationState = MutableStateFlow<DonationState>(DonationState.Idle)
    val signUpState: StateFlow<DonationState> = _donationState
    lateinit var donation: Donation

    fun donation() {
        viewModelScope.launch {
            userRepository.donation()
                .onStart { _donationState.value = DonationState.Loading }
                .catch { e -> _donationState.value = DonationState.Error(e.message ?: "냠") }
                .collect {
                    _donationState.value = DonationState.Success
                    donation = it
                }
        }
    }

    fun resetState() {
        _donationState.value = DonationState.Idle
    }
}

sealed class DonationState {
    object Idle : DonationState()
    object Loading : DonationState()
    object Success : DonationState()
    data class Error(val message: String) : DonationState()
}

data class Donation (
    val availableDonationTreeCount: Int,
    val donatedTrees: Int,
    val donationWon: Int
)