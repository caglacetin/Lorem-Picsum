package com.caglacetin.lorempicsum.ui.imagelist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caglacetin.lorempicsum.R
import com.caglacetin.lorempicsum.common.inflate
import com.caglacetin.lorempicsum.databinding.ItemImageBinding
import com.caglacetin.lorempicsum.ui.ImageItem
import com.caglacetin.lorempicsum.ui.imagelist.ImageListAdapter.ImageItemViewHolder
import javax.inject.Inject

class ImageListAdapter @Inject constructor() : RecyclerView.Adapter<ImageItemViewHolder>() {

  private val imageList: MutableList<ImageItem> = mutableListOf()
  var itemClicked: ((imageItem: ImageItem) -> Unit)? = null

  fun setImages(images: List<ImageItem>) {
    imageList.addAll(images)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ImageItemViewHolder {
    val itemBinding = parent.inflate<ItemImageBinding>(R.layout.item_image, false)
    return ImageItemViewHolder(itemBinding)
  }

  override fun onBindViewHolder(
    holder: ImageItemViewHolder,
    position: Int
  ) {
    holder.bind(imageList[position])
  }

  override fun getItemCount(): Int = imageList.size

  inner class ImageItemViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageItem: ImageItem) {
      with(binding) {
        viewState = ImageItemViewState(imageItem)
        executePendingBindings()
        itemView.setOnClickListener {
          itemClicked?.invoke(imageItem)
        }
      }
    }
  }

}
