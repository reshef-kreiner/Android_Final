package com.example.moviereviewsapp.view.fragment

import android.view.View
import androidx.core.view.isVisible
import com.example.moviereviewsapp.adapter.ListAdapter
import com.example.moviereviewsapp.databinding.ItemsBinding
import com.example.moviereviewsapp.viewmodel.ListViewModel

abstract class BaseListFragment<ItemType, VM : ListViewModel<ItemType>> : BaseFragment<VM>() {
    protected val binding by lazy { ItemsBinding.inflate(layoutInflater) }
    override val contentView by lazy { binding.root }

    override fun onSetup() {
        viewModel.apply {
            isEmpty.observe(this@BaseListFragment, this@BaseListFragment::showEmpty)

            items.observe(this@BaseListFragment) {
                adapter.items = it
            }

            isLoading.observe(this@BaseListFragment) {
                if (it) showEmpty(false)
            }
        }

        adapter.apply {
            setOnItemClickListener(viewModel::onItemClick)
            binding.itemsRecyclerView.adapter = this
        }
    }

    protected open fun showEmpty(isShow: Boolean) {
        binding.empty.emptyText.isVisible = isShow
    }


    abstract val adapter: ListAdapter<ItemType>
}