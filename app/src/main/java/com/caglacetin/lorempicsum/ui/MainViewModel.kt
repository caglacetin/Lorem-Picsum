package com.caglacetin.lorempicsum.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.data.ImagesRepository
import com.caglacetin.lorempicsum.data.response.Images
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val repository: ImagesRepository
): ViewModel() {

  @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
  val imagesLiveDataPrivate = MutableLiveData<Resource<Images>>()
  val imagesLiveData: LiveData<Resource<Images>> get() = imagesLiveDataPrivate


  fun getImages() {
    viewModelScope.launch {
      imagesLiveDataPrivate.value = Resource.Loading()
      repository.fetchImages().collect {
        imagesLiveDataPrivate.value = it
        }
    }
  }

}