package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class EncyclopediaVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GridFragment.newInstance("all")
            1 -> GridFragment.newInstance("animal")
            2 -> GridFragment.newInstance("plant")
            3 -> GridFragment.newInstance("insect")
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}