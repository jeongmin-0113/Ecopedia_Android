package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.base.BaseFragment
import com.ecopedia.ecopedia_android.databinding.FragmentEncyclopediaBinding
import com.ecopedia.ecopedia_android.presentation.encyclopedia.viewmodel.EncyclopediaViewModel
import com.ecopedia.ecopedia_android.presentation.signin.viewmodel.SignInViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EncyclopediaFragment :
    BaseFragment<FragmentEncyclopediaBinding>(
        FragmentEncyclopediaBinding::bind,
        R.layout.fragment_encyclopedia
    ) {


    private val tabInfo: ArrayList<String> = arrayListOf("전체", "식물", "동물", "곤충")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val encyclopediaAdapter = EncyclopediaVPAdapter(this)
        binding.encyclopediaContentVp.apply {
            adapter = encyclopediaAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(binding.encyclopediaTabTl, binding.encyclopediaContentVp) {
                tab, position ->
            tab.text = tabInfo[position] // 탭뷰의 아이템 이름 설정
        }.attach() // 탭뷰와 vp 연결

    }
}