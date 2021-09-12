package com.caglacetin.lorempicsum.util

import com.caglacetin.lorempicsum.ui.ImageItem

object DummyImageItem {
  fun createDummyImageItem(): ImageItem {
    return ImageItem(
      author = "Paul Jarvis",
      width = 5616,
      height = 3744,
      downloadUrl = "https://picsum.photos/id/1004/5616/3744"
    )
  }

  fun createDummyImageList(): List<ImageItem> {
    val imageList = mutableListOf<ImageItem>()
    imageList.add(createDummyImageItem())
    return imageList
  }

}
