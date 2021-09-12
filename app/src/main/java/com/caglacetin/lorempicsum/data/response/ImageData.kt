package com.caglacetin.lorempicsum.data.response

import com.squareup.moshi.Json

data class ImageData(
  val id: String,
  val author: String,
  val width: Long,
  val height: Long,
  val url: String,
  @Json(name = "download_url")
  val downloadUrl: String
)
