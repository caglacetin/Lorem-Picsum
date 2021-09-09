package com.caglacetin.lorempicsum.data

import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.data.remote.RemoteData
import com.caglacetin.lorempicsum.data.response.Images
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ImagesRepository @Inject constructor(
  private val remoteRepository: RemoteData,
  private val ioDispatcher: CoroutineContext
) {

  suspend fun fetchImages(): Flow<Resource<Images>> {
    return flow {
      emit(remoteRepository.requestImageList())
    }.flowOn(ioDispatcher)
  }

}
