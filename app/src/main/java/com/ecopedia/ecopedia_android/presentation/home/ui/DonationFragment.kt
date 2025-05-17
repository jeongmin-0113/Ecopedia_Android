package com.ecopedia.ecopedia_android.presentation.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.databinding.FragmentDonationBinding
import com.ecopedia.ecopedia_android.presentation.MainActivity
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.DonationViewModel
import com.ecopedia.ecopedia_android.presentation.home.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DonationFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentDonationBinding
    private val donationViewModel: DonationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonationBinding.inflate(layoutInflater)

        binding.donationCloseBtn.setOnClickListener {
            (context as MainActivity)
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.main, HomeFragment())
                .commitAllowingStateLoss()
        }

        binding.donationBtn.setOnClickListener {
            donationViewModel.donation()
            val donation = donationViewModel.donation

            binding.donationTv.setText(
                "지금까지 ${donation.donatedTrees}그루 후원하였습니다\n약 ${donation.donationWon}원"
            )
        }

        return binding.root
    }
}