package com.tunahanozatac.formula1apps.ui.details

import com.tunahanozatac.formula1apps.R
import com.tunahanozatac.formula1apps.core.BaseViewHolder
import com.tunahanozatac.formula1apps.databinding.ItemDetailsBinding
import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.util.extensions.loadImage

class DetailsViewHolder(
    private val binding: ItemDetailsBinding
) : BaseViewHolder<Details>(binding) {

    override fun bind(data: Details) {
        super.bind(data)
        with(binding) {
            imageDetail.loadImage(data.image)
            txtTeamInput.text = data.team
            ageInput.text = data.age.toString()
            if (data.isFavorite) {
                imgFav.setImageResource(R.drawable.ic_round_bookmark)
            } else {
                imgFav.setImageResource(R.drawable.ic_round_bookmark_border)
            }
        }
    }
}