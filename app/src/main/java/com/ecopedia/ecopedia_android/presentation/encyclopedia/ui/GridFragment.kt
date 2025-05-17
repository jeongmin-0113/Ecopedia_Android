package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ecopedia.ecopedia_android.databinding.FragmentGridBinding

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
        binding.wholeItemRv.adapter = adapter

        loadData(itemType)

        return binding.root
    }

    private fun loadData(type: String?) {
        // 예: 전체, 동물, 식물, 곤충 등
        // type 값에 따라 API 요청을 다르게 처리
        when (type) {
            "all" -> {}
            "animals" -> {/* 동물만 */}
            "plants" -> {/* 식물만 */}
            "insects" -> {/* 곤충만 */}
        }
    }
}

