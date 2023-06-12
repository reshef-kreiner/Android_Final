package com.example.moviereviewsapp.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.viewModels
import com.example.moviereviewsapp.databinding.AddMovieBinding
import com.example.moviereviewsapp.utils.isRTL
import com.example.moviereviewsapp.viewmodel.AddMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AddMovieFragment @Inject constructor() : BaseFragment<AddMovieViewModel>() {
    override val contentView by lazy { binding.root }
    override val viewModel: AddMovieViewModel by viewModels()

    private val binding by lazy { AddMovieBinding.inflate(layoutInflater) }
    private val pickImageLauncher =
        registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                handleImageIntent(result.data!!)
//                val originalUri = result.data!!.data
//                val takeFlags: Int = (result.data!!.flags
//                        and (Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION))
//                try {
//                    originalUri?.let {
//                        requireActivity().contentResolver.takePersistableUriPermission(
//                            it,
//                            takeFlags
//                        )
//                    }
//                } catch (e: SecurityException) {
//                    e.printStackTrace()
//                }
//                viewModel.onImagePicked(originalUri)
            }
        }


    override fun onSetup() {
        binding.apply {
            addMovieBackButton.setOnClickListener {
                viewModel.onBackClick()
            }

            addMovieSubmitButton.setOnClickListener {
                viewModel.onAddMovie(
                    title = binding.addMovieNameInput.editText?.text.toString(),
                    review = binding.addMovieReviewInput.editText?.text.toString(),
                    year = binding.addMovieYearInput.editText?.text?.let { Integer.parseInt(it.toString()) }
                        ?: 0
                )
            }

            addMovieImageButton.setOnClickListener {
                viewModel.onPickImage()
            }
        }

        viewModel.apply {
            pickImageEvent.observe(this@AddMovieFragment) {
                it?.ifNew?.let {
                    pickImageLauncher.launch(Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*"))
                }
            }

            pickedImagePath.observe(this@AddMovieFragment) {
                binding.addMovieImageStatusText.text = it
            }
        }
    }

    @SuppressLint("WrongConstant")
    fun handleImageIntent(intent: Intent) {
        val originalUri = intent.data
        val takeFlags: Int = (intent.flags
                and (Intent.FLAG_GRANT_READ_URI_PERMISSION
                or Intent.FLAG_GRANT_WRITE_URI_PERMISSION))
        try {
            originalUri?.let {
                requireActivity().contentResolver.takePersistableUriPermission(
                    it,
                    takeFlags
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
        viewModel.onImagePicked(originalUri)
    }
}