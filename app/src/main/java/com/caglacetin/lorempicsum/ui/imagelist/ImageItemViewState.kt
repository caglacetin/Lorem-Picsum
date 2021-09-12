package com.caglacetin.lorempicsum.ui.imagelist

import com.caglacetin.lorempicsum.ui.ImageItem

data class ImageItemViewState (
  private val imageItem: ImageItem)
{
  fun getImageUrl() = imageItem.downloadUrl
}
