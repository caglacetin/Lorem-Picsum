package com.caglacetin.lorempicsum.ui.imagedetail

import android.os.Bundle
import androidx.activity.viewModels
import com.caglacetin.lorempicsum.R
import com.caglacetin.lorempicsum.common.ImageBindingAdapter.setImageUrl
import com.caglacetin.lorempicsum.common.observe
import com.caglacetin.lorempicsum.data.response.ImageData
import com.caglacetin.lorempicsum.databinding.ActivityImageDetailBinding
import com.caglacetin.lorempicsum.ui.base.BaseActivity
import com.caglacetin.lorempicsum.ui.imagelist.IMAGE_DETAIL_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailActivity: BaseActivity() {

  private lateinit var binding: ActivityImageDetailBinding
  private val viewModel: ImageDetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.initIntentData(intent.getParcelableExtra(IMAGE_DETAIL_KEY) ?: ImageData())
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun initViewBinding() {
    binding = ActivityImageDetailBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  private fun listenToogleButtons(imageItem: ImageData) {
    binding.mtgImagetypeDetail.addOnButtonCheckedListener { group, checkedId, isChecked ->
      when (checkedId) {
        R.id.tb_original_image -> setImageUrl(binding.ivImageDetail, imageItem.downloadUrl)
        R.id.tb_grayscale_image -> setImageUrl(binding.ivImageDetail,"${imageItem.downloadUrl}/?grayscale")
        R.id.tb_blur_image -> setImageUrl(binding.ivImageDetail,"${imageItem.downloadUrl}/?blur=10")
      }
    }
  }

  override fun observeViewModel() {
    observe(viewModel.imageData, ::initializeView)
  }

  private fun initializeView(imageItem: ImageData) {
    binding.viewState = ImageDetailViewState(imageItem)
    listenToogleButtons(imageItem)
  }
}
