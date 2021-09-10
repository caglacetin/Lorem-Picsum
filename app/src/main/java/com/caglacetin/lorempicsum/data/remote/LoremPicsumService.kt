package com.caglacetin.lorempicsum.data.remote

import com.caglacetin.lorempicsum.data.response.ImageItem
import retrofit2.Response
import retrofit2.http.GET

interface LoremPicsumService {

  @GET("/v2/list")
  suspend fun fetchImages(): Response<List<ImageItem>>

  @GET("/id/list")
  suspend fun fetchImageDetail(): Response<ImageItem>

}
