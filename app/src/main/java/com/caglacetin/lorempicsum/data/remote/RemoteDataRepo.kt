package com.caglacetin.lorempicsum.data.remote

import com.caglacetin.lorempicsum.data.base.BaseDataSource
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.common.Resource.DataError
import com.caglacetin.lorempicsum.common.Resource.Loading
import com.caglacetin.lorempicsum.common.Resource.Success
import com.caglacetin.lorempicsum.data.response.ImageData
import javax.inject.Inject

class RemoteDataRepo @Inject constructor(
  private val service: LoremPicsumService,
): BaseDataSource(), RemoteDataSource {

  override suspend fun fetchImages(page: Int): Resource<List<ImageData>> {
    val response = processCall {
      service.fetchImages(page)
    }
    return when(response) {
      is DataError -> DataError(response.exception)
      is Loading -> Loading
      is Success -> response
    }
  }
}
