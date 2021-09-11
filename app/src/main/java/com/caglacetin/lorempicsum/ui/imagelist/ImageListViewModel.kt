package com.caglacetin.lorempicsum.ui.imagelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.data.ImagesRepository
import com.caglacetin.lorempicsum.data.response.ImageData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
  private val repository: ImagesRepository
): ViewModel() {

  private val imagesLiveDataPrivate = MutableLiveData<Resource<List<ImageData>>>()
  val imagesLiveData: LiveData<Resource<List<ImageData>>> get() = imagesLiveDataPrivate

  private val imageDetailPrivate = MutableLiveData<ImageData>()
  val openImageDetail: LiveData<ImageData> get() = imageDetailPrivate

  fun openImageDetail(imageItem: ImageData) {
    imageDetailPrivate.value = imageItem
  }

  fun getImages(page: Int) {
    viewModelScope.launch {
      imagesLiveDataPrivate.value = Resource.Loading
      repository.fetchImages(page).collect {
        imagesLiveDataPrivate.value = it
        }
    }
  }

}