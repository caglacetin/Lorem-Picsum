package com.caglacetin.lorempicsum.data.remote

import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.data.response.ImageItem
import com.caglacetin.lorempicsum.data.response.Images
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(
  private val service: LoremPicsumService,
) {

  suspend fun requestImageList(): Resource<Images> {
    return when (val response = processCall(service::fetchImages)) {
      is List<*> -> {
        Resource.Success(data = Images(response as ArrayList<ImageItem>))
      }
      else -> {
        Resource.DataError(errorCode = response as Int)
      }
    }
  }

  private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
    return try {
      val response = responseCall.invoke()
      val responseCode = response.code()
      if (response.isSuccessful) {
        response.body()
      } else {
        responseCode
      }
    } catch (e: IOException) {
      NETWORK_ERROR
    }
  }
}

const val NETWORK_ERROR = -2
