package com.caglacetin.lorempicsum.ui.imagelist

import com.caglacetin.lorempicsum.common.Resource

data class ImageListViewState(
  private val status: Resource<Any>
) {
  fun isLoading() = status is Resource.Loading
  fun getErrorMessage() = if (status is Resource.DataError) status.exception else ""
  fun shouldShowErrorMessage() = status is Resource.DataError
}