package com.caglacetin.lorempicsum.ui.imagelist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.caglacetin.lorempicsum.common.EndlessScrollListener
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.common.Resource.DataError
import com.caglacetin.lorempicsum.common.Resource.Loading
import com.caglacetin.lorempicsum.common.Resource.Success
import com.caglacetin.lorempicsum.common.Status
import com.caglacetin.lorempicsum.common.observe
import com.caglacetin.lorempicsum.data.response.ImageData
import com.caglacetin.lorempicsum.databinding.ActivityImageListBinding
import com.caglacetin.lorempicsum.ui.base.BaseActivity
import com.caglacetin.lorempicsum.ui.imagedetail.ImageDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageListActivity: BaseActivity() {

  private lateinit var binding: ActivityImageListBinding
  private val listViewModel: ImageListViewModel by viewModels()

  @Inject
  internal lateinit var imageListAdapter: ImageListAdapter

  override fun initViewBinding() {
    binding = ActivityImageListBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    listViewModel.getImages(FIRST_PAGE)
    initImageListRecyclerView()
  }

  private fun initImageListRecyclerView() {
    val linearLayoutManager = LinearLayoutManager(this)
    binding.recyclerviewImages.apply {
      adapter = imageListAdapter
      layoutManager = linearLayoutManager
      addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
        override fun onLoadMore(page: Int) {
          listViewModel.getImages(page)
        }
      })
    }

    imageListAdapter.itemClicked = {
      navigateToDetailScreen(it)
    }
  }

  override fun observeViewModel() {
    observe(listViewModel.imagesLiveData, ::handleImageList)
  }

  private fun handleImageList(status: Resource<List<ImageData>>) {
    when (status) {
      is Loading -> ImageListViewState(Status.Loading)
      is Success -> status.data.let { imageListAdapter.setImages(it) }
      is DataError -> Status.Error(status.exception)
    }
  }

  private fun navigateToDetailScreen(item: ImageData) {
      val nextScreenIntent = Intent(this, ImageDetailActivity::class.java)
        .apply { putExtra(IMAGE_DETAIL_KEY, item) }
      startActivity(nextScreenIntent)
  }
}

const val FIRST_PAGE = 1
const val IMAGE_DETAIL_KEY = "IMAGE_DETAIL_KEY"
