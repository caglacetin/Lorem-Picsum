package com.caglacetin.lorempicsum.ui.imagelist

import com.caglacetin.lorempicsum.data.response.ImageData

data class ImageItemViewState (
  private val imageItem: ImageData
  )
{
  fun getImageUrl() = imageItem.downloadUrl
}