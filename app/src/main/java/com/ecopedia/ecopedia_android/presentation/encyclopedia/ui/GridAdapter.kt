package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ecopedia.ecopedia_android.R

class GridItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listData: ArrayList<GridData> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return GridItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridItemViewHolder).onBind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    fun addItem(data: GridData) {
        listData.add(data)
    }
}


