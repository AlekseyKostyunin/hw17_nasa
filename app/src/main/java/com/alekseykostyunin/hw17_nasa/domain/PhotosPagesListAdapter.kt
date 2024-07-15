package com.alekseykostyunin.hw17_nasa.domain

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alekseykostyunin.hw17_nasa.databinding.PhotoItemBinding
import com.alekseykostyunin.hw17_nasa.entity.Photo
import com.bumptech.glide.Glide

class PhotosPagesListAdapter(
    private val onClick: (Photo) -> Unit
) : PagingDataAdapter<Photo, PhotosViewHolder>(DiffUtilCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            item?.let {
                rover.text = "Rover: ${item.rover.name}"
                sol.text = "Sol: ${item.sol}"
                camera.text = "Camera: ${item.camera.name}"
                earthDate.text = "Date: ${item.earthDate}"
                Glide
                    .with(photoItem.context)
                    .load(item.imgSrc)
                    .centerCrop()
                    .into(imageViewPhoto)
            }
        }

        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

}

class PhotosViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean = oldItem == newItem
}