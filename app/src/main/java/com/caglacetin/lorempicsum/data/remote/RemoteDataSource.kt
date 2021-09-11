package com.caglacetin.lorempicsum.data.remote

import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.data.response.ImageData

interface RemoteDataSource {
  suspend fun fetchImages(page: Int): Resource<List<ImageData>>
}
