package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.data.source.remote.Item

class GridItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData: List<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return GridItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridItemViewHolder).onBind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    fun setData(data: List<Item>) {
        listData = data
    }

    fun releaseData() {
        listData = ArrayList()
    }
}


