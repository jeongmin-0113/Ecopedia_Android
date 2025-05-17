package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ecopedia.ecopedia_android.R

class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemCardImage: ImageView
    var itemCardTitle: TextView

    init {
        itemCardImage = itemView.findViewById(R.id.item_card_iv)
        itemCardTitle = itemView.findViewById(R.id.item_card_tv)
    }

    fun onBind(data: GridData) {
        itemCardImage.setImageResource(data.image)
        itemCardTitle.setText(data.title)
    }
}