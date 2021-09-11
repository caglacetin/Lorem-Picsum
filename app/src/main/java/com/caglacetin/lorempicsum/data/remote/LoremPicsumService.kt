package com.caglacetin.lorempicsum.data.remote

import com.caglacetin.lorempicsum.data.response.ImageData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoremPicsumService {

  @GET("/v2/list")
  suspend fun fetchImages(@Query("page") page: Int): Response<List<ImageData>>

}
