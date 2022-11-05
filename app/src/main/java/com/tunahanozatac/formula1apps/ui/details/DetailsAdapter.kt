package com.tunahanozatac.formula1apps.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.formula1apps.core.BaseAdapter
import com.tunahanozatac.formula1apps.databinding.ItemDetailsBinding
import com.tunahanozatac.formula1apps.domain.model.Details
import com.tunahanozatac.formula1apps.util.adapter.getDiffUtilCallBack

class DetailsAdapter : BaseAdapter<Details, RecyclerView.ViewHolder>(
    getDiffUtilCallBack()
) {

    private lateinit var itemDetailsBinding: ItemDetailsBinding

    override fun bindView(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailsViewHolder).bind(currentList[position])
    }

    override fun createView(
        context: Context, parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        itemDetailsBinding = ItemDetailsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DetailsViewHolder(itemDetailsBinding)
    }
}