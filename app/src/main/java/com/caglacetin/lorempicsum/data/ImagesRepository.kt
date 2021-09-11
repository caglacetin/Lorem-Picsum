package com.caglacetin.lorempicsum.data

import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.data.remote.RemoteDataRepo
import com.caglacetin.lorempicsum.data.response.ImageData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ImagesRepository @Inject constructor(
  private val remoteRepository: RemoteDataRepo,
  private val ioDispatcher: CoroutineContext
) {

  suspend fun fetchImages(page: Int): Flow<Resource<List<ImageData>>> {
    return flow {
      emit(remoteRepository.fetchImages(page))
    }.flowOn(ioDispatcher)
  }

}
