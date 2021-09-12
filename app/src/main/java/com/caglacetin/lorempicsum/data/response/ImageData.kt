package com.caglacetin.lorempicsum.data.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageData(
  val id: String = "",
  val author: String = "",
  val width: Long = 0,
  val height: Long = 0,
  val url: String = "",
  @Json(name = "download_url")
  val downloadUrl: String = ""
) : Parcelable
