package com.caglacetin.lorempicsum.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageItem(
  val author: String = "",
  val width: Long = 0,
  val height: Long = 0,
  val downloadUrl: String = ""
): Parcelable
