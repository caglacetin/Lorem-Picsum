package com.caglacetin.lorempicsum.ui.imagelist

import com.caglacetin.lorempicsum.common.Status

data class ImageListViewState(
  private val status: Status
) {

  fun isLoading() = status is Status.Loading
  fun getErrorMessage() = if (status is Status.Error) status.exception else ""
  fun shouldShowErrorMessage() = status is Status.Error

}
