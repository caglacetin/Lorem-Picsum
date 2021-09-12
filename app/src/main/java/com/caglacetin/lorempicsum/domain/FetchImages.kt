package com.caglacetin.lorempicsum.domain

import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.common.map
import com.caglacetin.lorempicsum.data.ImagesRepository
import com.caglacetin.lorempicsum.ui.ImageItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchImages @Inject constructor(
  private val repository: ImagesRepository,
  private val mapper: ImageListMapper
) {
  suspend fun fetchImages(page: Int): Flow<Resource<List<ImageItem>>> =
    repository.fetchImages(page).map { resource ->
      resource.map { response ->
        mapper.mapFrom(response)
      }
    }
}