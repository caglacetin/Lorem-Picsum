package com.caglacetin.lorempicsum.ui.imagelist

import com.caglacetin.lorempicsum.data.response.ImageItem

data class ImageItemViewState (
  private val imageItem: ImageItem
  )
{
  fun getImageUrl() = imageItem.downloadUrl
}