package com.ecopedia.ecopedia_android.presentation.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.databinding.FragmentDonationBinding
import com.ecopedia.ecopedia_android.presentation.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DonationFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentDonationBinding

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
            // todo: 후원
        }

        return binding.root
    }
}