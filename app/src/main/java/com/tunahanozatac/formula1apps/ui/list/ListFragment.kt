package com.tunahanozatac.formula1apps.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahanozatac.formula1apps.R
import com.tunahanozatac.formula1apps.core.BaseFragment
import com.tunahanozatac.formula1apps.databinding.FragmentListBinding
import com.tunahanozatac.formula1apps.domain.model.Item
import com.tunahanozatac.formula1apps.util.extensions.listen
import com.tunahanozatac.formula1apps.util.extensions.toast
import com.tunahanozatac.formula1apps.util.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding, ListViewModel>(), ItemClickListener {

    override val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: ListAdapter

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }

    override fun viewCreated() {
        setAdapter()
    }

    override fun observerData() {
        super.observerData()
        lifecycleScope.launch {
            viewModel.getList().listen {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        it.data?.items?.sortedByDescending { sorted -> sorted.point }
                        adapter.submitList(it.data?.items)
                        configureVisibility(binding?.pb, false)
                    }
                    UIStatus.ERROR -> {
                        configureVisibility(binding?.pb, false)
                        requireActivity() toast (it.message.toString())
                    }
                    UIStatus.LOADING -> {
                        configureVisibility(binding?.pb, true)
                    }
                    else -> {
                        requireContext() toast getString(R.string.somethingWentWrong)
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = ListAdapter {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailsFragment(
                    it
                )
            )
        }
        adapter.listener = this
        binding?.recyclerViewList?.adapter = adapter
        binding?.recyclerViewList?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onButtonClick(item: Item) {
        if (item.isFavorite) {
            viewModel.addToFavorite(item)
        } else {
            viewModel.deleteFromFavorites(item.id)
        }
    }
}