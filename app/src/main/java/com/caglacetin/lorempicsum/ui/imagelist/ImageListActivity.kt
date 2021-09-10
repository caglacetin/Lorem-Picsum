package com.caglacetin.lorempicsum.ui.imagelist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.common.Resource.DataError
import com.caglacetin.lorempicsum.common.Resource.Loading
import com.caglacetin.lorempicsum.common.Resource.Success
import com.caglacetin.lorempicsum.common.Status
import com.caglacetin.lorempicsum.common.observe
import com.caglacetin.lorempicsum.data.response.ImageItem
import com.caglacetin.lorempicsum.data.response.Images
import com.caglacetin.lorempicsum.databinding.ActivityImageListBinding
import com.caglacetin.lorempicsum.ui.base.BaseActivity
import com.caglacetin.lorempicsum.ui.imagedetail.ImageDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageListActivity: BaseActivity() {

  private lateinit var binding: ActivityImageListBinding

  @Inject
  internal lateinit var imageListAdapter: ImageListAdapter

  private val listViewModel: ImageListViewModel by viewModels()

  override fun initViewBinding() {
    binding = ActivityImageListBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    listViewModel.getImages()
    initImageListRecyclerView()
  }

  private fun initImageListRecyclerView() {
    val linearLayoutManager = LinearLayoutManager(this)
    binding.recyclerviewImages.apply {
      adapter = imageListAdapter
      layoutManager = linearLayoutManager
    }

    imageListAdapter.itemClicked = {
      navigateToDetailScreen(it)
    }
  }

  override fun observeViewModel() {
    observe(listViewModel.imagesLiveData, ::handleImageList)
  }

  private fun handleImageList(status: Resource<Images>) {
    when (status) {
      is Loading -> {
        Status.Loading
        ImageListViewState(Status.Loading)
      }
      is Success -> status.data?.let {
        Status.Content
        imageListAdapter.setImages(it.imageList)
      }
      is DataError -> Status.Error(status.errorCode)
    }
  }

  private fun navigateToDetailScreen(item: ImageItem) {
      val nextScreenIntent = Intent(this, ImageDetailActivity::class.java)
        .apply { putExtra(IMAGE_ITEM_KEY, item) }
      startActivity(nextScreenIntent)
  }

}

const val IMAGE_ITEM_KEY = "IMAGE_ITEM_KEY"
