package com.caglacetin.lorempicsum.ui.imagedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caglacetin.lorempicsum.ui.ImageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(): ViewModel() {

  private val imagePrivate = MutableLiveData<ImageItem>()
  val imageData: LiveData<ImageItem> get() = imagePrivate

  fun initIntentData(image: ImageItem) {
    imagePrivate.value = image
  }
}
