package com.tunahanozatac.formula1apps.ui.list

import com.tunahanozatac.formula1apps.R
import com.tunahanozatac.formula1apps.core.BaseViewHolder
import com.tunahanozatac.formula1apps.databinding.ItemListBinding
import com.tunahanozatac.formula1apps.domain.model.Item

class ListViewHolder(
    private val binding: ItemListBinding
) : BaseViewHolder<Item>(binding) {

    var clickListener: ClickListener? = null

    override fun bind(data: Item, onItemClick: ((Item) -> Unit)?) {

        super.bind(data, onItemClick)
        with(binding) {
            if (data.isFavorite) {
                imgFav.setImageResource(R.drawable.ic_round_bookmark)
            } else {
                imgFav.setImageResource(R.drawable.ic_round_bookmark_border)
            }
            nameTitle.text = data.name
            point.text = data.point.toString()
            constraintLayout.setOnClickListener {
                onItemClick?.invoke(data)
            }
            imgFav.setOnClickListener {
                if (data.isFavorite) {
                    data.isFavorite = false
                    clickListener?.onClick(data, bindingAdapterPosition)
                    imgFav.setImageResource(R.drawable.ic_round_bookmark_border)
                } else {
                    data.isFavorite = true
                    clickListener?.onClick(data, bindingAdapterPosition)
                    imgFav.setImageResource(R.drawable.ic_round_bookmark)
                }
            }
        }
    }
}

interface ClickListener {
    fun onClick(item: Item, newPosition: Int)
}