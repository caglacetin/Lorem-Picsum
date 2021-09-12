package com.caglacetin.lorempicsum.ui.imagedetail

import com.caglacetin.lorempicsum.ui.ImageItem

data class ImageDetailViewState(
  private val image: ImageItem
) {
  fun getAuthor() = image.author
  fun getDimensions() = "${image.width} x ${image.height}"
  fun getOriginalImage() = image.downloadUrl
}
