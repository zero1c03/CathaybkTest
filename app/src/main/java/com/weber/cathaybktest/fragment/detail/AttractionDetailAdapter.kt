package com.weber.cathaybktest.fragment.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.weber.cathaybktest.R
import com.weber.cathaybktest.databinding.ListDetailImageBinding
import com.weber.cathaybktest.model.Images

class AttractionDetailAdapter :
    ListAdapter<Images, AttractionDetailAdapter.DetailViewHolder>(DetailDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ListDetailImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val imageItem = getItem(position)
        holder.bind(imageItem)
    }

    class DetailViewHolder(binding: ListDetailImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageView: ImageView = binding.carouselImageView
        fun bind(image: Images) {
            imageView.load(image.src) {
                placeholder(R.drawable.placeholder_inset)
            }
        }
    }

    class DetailDiffCallback : DiffUtil.ItemCallback<Images>() {
        override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem.src == newItem.src
        }

        override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
            return oldItem.ext == newItem.ext
        }

    }

}