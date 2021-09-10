package com.caglacetin.lorempicsum.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.common.observe
import com.caglacetin.lorempicsum.data.response.Images
import com.caglacetin.lorempicsum.databinding.ActivityImageListBinding
import com.caglacetin.lorempicsum.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListActivity : BaseActivity() {

  private lateinit var binding: ActivityImageListBinding

  private val listViewModel: ImageListViewModel by viewModels()

  override fun initViewBinding() {
    binding = ActivityImageListBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    listViewModel.getImages()
  }

  override fun observeViewModel() {
    observe(listViewModel.imagesLiveData, ::handleRecipesList)
  }

  private fun handleRecipesList(status: Resource<Images>) {
    when (status) {
      is Resource.Success -> status.data?.let {
        Log.e("response", it.toString())
      }
    }
  }

}
