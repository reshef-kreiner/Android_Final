package com.example.moviereviewsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviereviewsapp.utils.NavigationCommand
import com.example.moviereviewsapp.view.BaseView
import com.example.moviereviewsapp.view.activity.BaseActivity
import com.example.moviereviewsapp.viewmodel.BaseViewModel

abstract class BaseFragment<out VM : BaseViewModel> : Fragment(), BaseView<VM> {
    private var _onFinishListener: () -> Unit = {}

    val args get() = arguments ?: Bundle()
    val _baseActivity get() = (activity as BaseActivity<*>)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = contentView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetupHook()

        _baseActivity.onFragmentSetup(this)

        viewModel.apply {
            errorEvent.observe(this@BaseFragment) {
                it.ifNew?.let(this@BaseFragment::showError)
            }

            messageEvent.observe(this@BaseFragment) {
                it.ifNew?.let(this@BaseFragment::showMessage)
            }

            navigationEvent.observe(this@BaseFragment) {
                it?.ifNew?.let {
                    when (it) {
                        is NavigationCommand.ToDirection -> findNavController().navigate(it.directions)
                        is NavigationCommand.Back -> findNavController().navigateUp()
                    }
                }
            }
        }
    }

    override fun showError(@StringRes stringResId: Int) {
        _baseActivity.viewModel.onError(stringResId)
    }

    override fun showMessage(@StringRes stringResId: Int) {
        _baseActivity.viewModel.onMessage(stringResId)
    }

    protected open fun onSetupHook() {
        onSetup()
        viewModel.attach()
    }

    fun setOnFinishListener(onFinishListener: () -> Unit) {
        _onFinishListener = onFinishListener
    }


    abstract val contentView: View
    abstract override val viewModel: VM
}