package com.caglacetin.lorempicsum.ui.imagedetail

import android.os.Bundle
import androidx.activity.viewModels
import com.caglacetin.lorempicsum.common.observe
import com.caglacetin.lorempicsum.data.response.ImageItem
import com.caglacetin.lorempicsum.databinding.ActivityImageDetailBinding
import com.caglacetin.lorempicsum.ui.base.BaseActivity
import com.caglacetin.lorempicsum.ui.imagelist.IMAGE_ITEM_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailActivity: BaseActivity() {

  private lateinit var binding: ActivityImageDetailBinding
  private val viewModel: ImageDetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.initIntentData(intent.getParcelableExtra(IMAGE_ITEM_KEY) ?: ImageItem())
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun initViewBinding() {
    binding = ActivityImageDetailBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  override fun observeViewModel() {
    observe(viewModel.imageData, ::initializeView)
  }

  private fun initializeView(imageItem: ImageItem) {
    binding.viewState = ImageDetailViewState(imageItem)
  }
}
