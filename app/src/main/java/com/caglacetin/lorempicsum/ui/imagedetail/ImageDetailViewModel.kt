package com.caglacetin.lorempicsum.ui.imagedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caglacetin.lorempicsum.data.response.ImageData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(): ViewModel() {

  private val imagePrivate = MutableLiveData<ImageData>()
  val imageData: LiveData<ImageData> get() = imagePrivate

  fun initIntentData(image: ImageData) {
    imagePrivate.value = image
  }


}