package com.weber.cathaybktest.fragment.attractions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.weber.cathaybktest.R
import com.weber.cathaybktest.databinding.ListAttractionBinding
import com.weber.cathaybktest.model.AttractionsDetail


class AttractionsAdapter :
    PagingDataAdapter<AttractionsDetail, AttractionsAdapter.AttractionsViewHolder>(
        AttractionsDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionsViewHolder {
        return AttractionsViewHolder(
            ListAttractionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AttractionsViewHolder, position: Int) {
        val attractionsItem = getItem(position)
        holder.bind(attractionsItem)
    }

    class AttractionsViewHolder(private val binding: ListAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image = binding.ivAttractionIcon
        private val title = binding.tvAttractionTitle
        private val desc = binding.tvAttractionDesc


        fun bind(detail: AttractionsDetail?) {
            binding.root.setOnClickListener {
                detail?.let { detail ->
                    val bundle = bundleOf("detail" to detail)
                    it.findNavController().navigate(R.id.start_AttractionDetailFragment, bundle)
                }

            }

            detail?.let {
                if (!detail.images.isNullOrEmpty()) {
                    image.load(detail.images?.get(0)?.src) {
                        placeholder(R.drawable.ic_placeholder)
                    }
                } else {
                    image.load(R.drawable.ic_placeholder)
                }
                title.text = detail.name ?: ""
                desc.text = detail.introduction ?: ""
            }
        }
    }

    class AttractionsDiffCallback : DiffUtil.ItemCallback<AttractionsDetail>() {
        override fun areItemsTheSame(
            oldItem: AttractionsDetail,
            newItem: AttractionsDetail
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AttractionsDetail,
            newItem: AttractionsDetail
        ): Boolean {
            return oldItem.introduction == newItem.introduction &&
                    oldItem.name == newItem.name &&
                    oldItem.address == newItem.address
        }

    }

}