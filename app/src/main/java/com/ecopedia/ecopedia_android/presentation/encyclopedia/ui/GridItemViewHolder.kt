package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ecopedia.ecopedia_android.R

class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemCardImage: ImageView
    private val itemCardTitle: TextView

    init {
        itemCardImage = itemView.findViewById(R.id.item_card_iv)
        itemCardTitle = itemView.findViewById(R.id.item_card_tv)
    }

    fun onBind(data: GridData) {
        itemCardImage.setImageResource(data.imageUrl)
        itemCardTitle.setText(data.creatureName)
    }
}