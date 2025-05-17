package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ecopedia.ecopedia_android.databinding.FragmentGridBinding
import com.ecopedia.ecopedia_android.presentation.encyclopedia.viewmodel.EncyclopediaViewModel
import com.ecopedia.ecopedia_android.presentation.encyclopedia.viewmodel.ItemReceiveUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GridFragment : Fragment() {

    companion object {
        private const val ARG_ITEM_TYPE = "item_type"

        fun newInstance(type: String): GridFragment {
            val fragment = GridFragment()
            val args = Bundle()
            args.putString(ARG_ITEM_TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }

    private var itemType: String? = null
    private lateinit var binding: FragmentGridBinding
    private var adapter: GridItemAdapter? = null
    private val encyclopediaViewModel: EncyclopediaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemType = arguments?.getString(ARG_ITEM_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGridBinding.inflate(inflater, container, false)
        adapter = GridItemAdapter()
        encyclopediaViewModel.getAllItems()
        adapter!!.setData(loadData(itemType))
        binding.gridItemRv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            encyclopediaViewModel.itemState.collect { result ->
                when (result) {
                    is ItemReceiveUiState.Success -> {
                        adapter!!.setData(loadData(itemType))
                    }

                    is ItemReceiveUiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            result.message ?: "오류가 발생했습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {

                    }
                }
            }
        }

        return binding.root
    }

    private fun loadData(type: String?) = when (type) {
        "all" -> {
            encyclopediaViewModel.itemList
        }

        "animals" -> {
            encyclopediaViewModel.itemList.filter { it.category == "ANIMALS" }
        }

        "plants" -> {
            encyclopediaViewModel.itemList.filter { it.category == "PLANTS" }
        }

        "insects" -> {
            encyclopediaViewModel.itemList.filter { it.category == "INSECTS" }
        }

        else -> {
            encyclopediaViewModel.itemList
        }
    }
}

