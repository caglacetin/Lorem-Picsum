package com.caglacetin.lorempicsum.ui.imagedetail

import com.caglacetin.lorempicsum.data.response.ImageData

data class ImageDetailViewState(
  private val image: ImageData
) {

  fun getAuthor() = image.author

  fun getDimensions() = "${image.width} x ${image.height}"

  fun getOriginalImage() = image.downloadUrl

}
