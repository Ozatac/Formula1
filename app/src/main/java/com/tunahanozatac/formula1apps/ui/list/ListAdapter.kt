package com.tunahanozatac.formula1apps.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.formula1apps.core.BaseAdapter
import com.tunahanozatac.formula1apps.databinding.ItemListBinding
import com.tunahanozatac.formula1apps.domain.model.Item
import com.tunahanozatac.formula1apps.util.adapter.getDiffUtilCallBack

class ListAdapter(
    var onClick: (Item) -> Unit,
) : BaseAdapter<Item, RecyclerView.ViewHolder>(
    getDiffUtilCallBack()
), ClickListener {
    private lateinit var bindingItemListBinding: ItemListBinding
    var listener: ItemClickListener? = null

    override fun bindView(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListViewHolder).bind(currentList[position]) {
            onClick.invoke(it)
        }
        holder.clickListener = this
    }

    override fun createView(
        context: Context, parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        bindingItemListBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(bindingItemListBinding)
    }

    override fun onClick(item: Item, newPosition: Int) {
        listener?.onButtonClick(item = item)
    }
}

interface ItemClickListener {
    fun onButtonClick(item: Item)
}