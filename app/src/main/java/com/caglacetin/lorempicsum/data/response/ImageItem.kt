package com.caglacetin.lorempicsum.data.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageItem(
  val id: String = "",
  val author: String = "",
  val width: Int = 0,
  val height: Int = 0,
  val url: String = "",
  @Json(name = "download_url")
  val downloadUrl: String = ""
) : Parcelable
