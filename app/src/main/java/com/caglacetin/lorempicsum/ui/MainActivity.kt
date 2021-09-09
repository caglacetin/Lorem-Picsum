package com.caglacetin.lorempicsum.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.common.observe
import com.caglacetin.lorempicsum.data.response.Images
import com.caglacetin.lorempicsum.databinding.ActivityMainBinding
import com.caglacetin.lorempicsum.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding

  private val mainViewModel: MainViewModel by viewModels()

  override fun initViewBinding() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainViewModel.getImages()
  }

  override fun observeViewModel() {
    observe(mainViewModel.imagesLiveData, ::handleRecipesList)
  }

  private fun handleRecipesList(status: Resource<Images>) {
    when (status) {
      is Resource.Success -> status.data?.let {
        Log.e("response", it.toString())
      }
    }
  }

}
