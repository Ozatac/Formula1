package com.tunahanozatac.formula1apps.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahanozatac.formula1apps.R
import com.tunahanozatac.formula1apps.core.BaseFragment
import com.tunahanozatac.formula1apps.databinding.FragmentDetailsBinding
import com.tunahanozatac.formula1apps.util.extensions.listen
import com.tunahanozatac.formula1apps.util.extensions.toast
import com.tunahanozatac.formula1apps.util.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {

    override val viewModel: DetailsViewModel by viewModels()
    private lateinit var adapter: DetailsAdapter
    private val args: DetailsFragmentArgs by navArgs()
    private var detailsId = 1

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun viewCreated() {
        binding?.apply {
            args.data.let {
                detailsId = it.id
                setAdapter()
            }
        }
    }

    override fun observerData() {
        super.observerData()
        lifecycleScope.launch {
            viewModel.getDetails(id = detailsId).listen {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        it.data?.isFavorite = args.data.isFavorite
                        adapter.submitList(listOf(it.data))
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
        adapter = DetailsAdapter()
        binding?.recyclerViewDetails?.adapter = adapter
        binding?.recyclerViewDetails?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}