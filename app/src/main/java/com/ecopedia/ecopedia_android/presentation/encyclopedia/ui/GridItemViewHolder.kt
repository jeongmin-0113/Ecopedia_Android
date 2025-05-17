package com.ecopedia.ecopedia_android.presentation.encyclopedia.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ecopedia.ecopedia_android.R
import com.ecopedia.ecopedia_android.data.source.remote.Item

class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemCardImage: ImageView
    private val itemCardTitle: TextView

    init {
        itemCardImage = itemView.findViewById(R.id.item_card_iv)
        itemCardTitle = itemView.findViewById(R.id.item_card_tv)
    }

    fun onBind(data: Item) {
        val svgImageUrl: String = data.imageUrl // 불러올 SVG 이미지 URL

        itemCardImage.load(svgImageUrl)
        itemCardTitle.setText(data.creatureName)
    }
}