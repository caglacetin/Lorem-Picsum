package com.caglacetin.lorempicsum.ui.imagedetail

import com.caglacetin.lorempicsum.data.response.ImageItem

data class ImageDetailViewState(
  private val image: ImageItem
) {

  fun getAuthor() = image.author

  fun getBlurImage() = "${image.downloadUrl}?blur"

  fun getGrayscaleImage() = "${image.downloadUrl}?grayscale"

}
