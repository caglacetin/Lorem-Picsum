package com.caglacetin.lorempicsum.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caglacetin.lorempicsum.common.Resource
import com.caglacetin.lorempicsum.domain.FetchImages
import com.caglacetin.lorempicsum.ui.ImageItem
import com.caglacetin.lorempicsum.ui.imagelist.ImageListViewModel
import com.caglacetin.lorempicsum.util.DummyImageItem.createDummyImageList
import com.caglacetin.lorempicsum.util.InstantExecutorExtension
import com.caglacetin.lorempicsum.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.Exception

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class ImageListViewModelTest {

  @MockK
  lateinit var fetchImagesUseCase: FetchImages

  // Set the main coroutines dispatcher for unit testing.
  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  // Executes each task synchronously using Architecture Components.
  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var imageListViewModel: ImageListViewModel

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    imageListViewModel = ImageListViewModel(fetchImagesUseCase)
  }

  @Test
  fun `given success state, when fetchImages called`() {
    val successStatus = Resource.Success(createDummyImageList())

    coEvery { fetchImagesUseCase.fetchImages(1) } returns flow {
      emit(successStatus)
    }

    imageListViewModel.getImages(1)
    imageListViewModel.imagesLiveData.observeForever { }

    assertEquals(successStatus, imageListViewModel.imagesLiveData.value)
  }

  @Test
  fun `given error state, when fetchImages called`() {
    val exception = Resource.DataError(Exception())
    val error: Resource<List<ImageItem>> = exception

    coEvery { fetchImagesUseCase.fetchImages(1) } returns flow {
      emit(error)
    }

    imageListViewModel.getImages(1)
    imageListViewModel.imagesLiveData.observeForever { }

    assertEquals(exception, imageListViewModel.imagesLiveData.value)
  }

}
