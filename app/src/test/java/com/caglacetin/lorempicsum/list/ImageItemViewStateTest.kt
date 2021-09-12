package com.caglacetin.lorempicsum.list

import com.caglacetin.lorempicsum.util.DummyImageItem.createDummyImageItem
import com.caglacetin.lorempicsum.ui.imagelist.ImageItemViewState
import com.google.common.truth.Truth
import org.junit.Test

class ImageItemViewStateTest {

  @Test
  fun `should match image url for given image item`() {
    // Given
    val imageItem = createDummyImageItem()
    val givenViewState = ImageItemViewState(imageItem)
    // When
    val actualResult = givenViewState.getImageUrl()
    // Then
    Truth.assertThat(actualResult).isEqualTo(imageItem.downloadUrl)
  }
}
