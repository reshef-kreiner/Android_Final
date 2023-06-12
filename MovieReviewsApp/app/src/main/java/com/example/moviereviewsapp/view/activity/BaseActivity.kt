package com.example.moviereviewsapp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviereviewsapp.view.BaseView
import com.example.moviereviewsapp.view.fragment.BaseFragment
import com.example.moviereviewsapp.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), BaseView<VM> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView?.let { setContentView(it) }

        onSetup()

        viewModel.apply {
            attach()

            errorEvent.observe(this@BaseActivity) {
                it.ifNew?.let(this@BaseActivity::showError)
            }

            messageEvent.observe(this@BaseActivity) {
                it.ifNew?.let(this@BaseActivity::showMessage)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.detach()
    }

    override fun showError(stringResId: Int) {
        Toast.makeText(
            applicationContext,
            getString(stringResId),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showMessage(stringResId: Int) {
        Toast.makeText(
            this,
            getString(stringResId),
            Toast.LENGTH_SHORT
        ).show()
    }

    open fun <VS : BaseViewModel> onFragmentSetup(fragment: BaseFragment<VS>) {}

    abstract val contentView: View?
    abstract override val viewModel: VM
}