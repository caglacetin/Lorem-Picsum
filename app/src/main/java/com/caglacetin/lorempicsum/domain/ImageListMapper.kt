package com.caglacetin.lorempicsum.domain

import com.caglacetin.lorempicsum.common.Mapper
import com.caglacetin.lorempicsum.data.response.ImageData
import com.caglacetin.lorempicsum.ui.ImageItem
import javax.inject.Inject

class ImageListMapper @Inject constructor() : Mapper<List<ImageData>, List<ImageItem>> {

  override fun mapFrom(type: List<ImageData>): List<ImageItem> =
    type.map { imageData ->
      ImageItem(
        author = imageData.author,
        width = imageData.width,
        height = imageData.height,
        downloadUrl = imageData.downloadUrl
      )
    }
}
