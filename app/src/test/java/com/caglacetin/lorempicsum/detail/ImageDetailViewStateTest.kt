package com.caglacetin.lorempicsum.detail

import com.caglacetin.lorempicsum.util.DummyImageItem
import com.caglacetin.lorempicsum.ui.imagedetail.ImageDetailViewState
import com.google.common.truth.Truth
import org.junit.Test

class ImageDetailViewStateTest {

  @Test
  fun `should match image author for given image item`() {
    // Given
    val imageItem = DummyImageItem.createDummyImageItem()
    val givenViewState = ImageDetailViewState(imageItem)
    // When
    val actualResult = givenViewState.getAuthor()
    // Then
    Truth.assertThat(actualResult).isEqualTo(imageItem.author)
  }

  @Test
  fun `should match image width and height for given image item`() {
    // Given
    val imageItem = DummyImageItem.createDummyImageItem()
    val givenViewState = ImageDetailViewState(imageItem)
    // When
    val actualResult = givenViewState.getDimensions()
    // Then
    Truth.assertThat(actualResult).isEqualTo("${imageItem.width} x ${imageItem.height}")
  }

  @Test
  fun `should match image url for given image item`() {
    // Given
    val imageItem = DummyImageItem.createDummyImageItem()
    val givenViewState = ImageDetailViewState(imageItem)
    // When
    val actualResult = givenViewState.getOriginalImage()
    // Then
    Truth.assertThat(actualResult).isEqualTo(imageItem.downloadUrl)
  }

}
