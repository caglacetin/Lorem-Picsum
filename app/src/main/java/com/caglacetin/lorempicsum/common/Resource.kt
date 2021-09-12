package com.caglacetin.lorempicsum.common

// references :
// https://developer.android.com/jetpack/docs/guide#addendum

sealed class Resource<out T> {
  class Success<T>(val data: T) : Resource<T>()
  class DataError(val exception: Throwable) : Resource<Nothing>()
  object Loading : Resource<Nothing>()
}
