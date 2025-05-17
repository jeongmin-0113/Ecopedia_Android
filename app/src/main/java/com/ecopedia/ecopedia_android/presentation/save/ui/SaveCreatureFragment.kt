package com.ecopedia.ecopedia_android.presentation.save.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentSaveCreatureBinding
import com.ecopedia.ecopedia_android.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveCreatureFragment :
    BaseFragment<FragmentSaveCreatureBinding>(
        FragmentSaveCreatureBinding::bind,
        R.layout.fragment_save_creature
    ) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}