package com.caglacetin.lorempicsum.ui.imagelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.domain.FetchImages
import com.caglacetin.lorempicsum.ui.ImageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
  private val usaCase: FetchImages
): ViewModel() {

  private val imagesLiveDataPrivate = MutableLiveData<Resource<List<ImageItem>>>()
  val imagesLiveData: LiveData<Resource<List<ImageItem>>> get() = imagesLiveDataPrivate

  private val imageDetailPrivate = MutableLiveData<ImageItem>()
  val openImageDetail: LiveData<ImageItem> get() = imageDetailPrivate

  fun openImageDetail(imageItem: ImageItem) {
    imageDetailPrivate.value = imageItem
  }

  fun getImages(page: Int) {
    viewModelScope.launch {
      imagesLiveDataPrivate.value = Resource.Loading
      usaCase.fetchImages(page).collect {
        imagesLiveDataPrivate.value = it
        }
    }
  }

}