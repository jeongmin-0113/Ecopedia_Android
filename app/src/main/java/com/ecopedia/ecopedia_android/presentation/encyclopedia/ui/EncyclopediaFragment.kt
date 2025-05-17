package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.os.Bundle
import android.view.View
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentEncyclopediaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EncyclopediaFragment :
    BaseFragment<FragmentEncyclopediaBinding>(
        FragmentEncyclopediaBinding::bind,
        R.layout.fragment_encyclopedia
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}