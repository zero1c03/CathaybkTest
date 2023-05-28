package com.weber.cathaybktest.fragment.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.carousel.CarouselLayoutManager
import com.weber.cathaybktest.R
import com.weber.cathaybktest.databinding.FragmentDetailBinding
import com.weber.cathaybktest.fragment.BaseFragment
import com.weber.cathaybktest.model.AttractionsDetail
import com.weber.cathaybktest.utils.Constants.hyperlinkStyle

class AttractionDetailFragment : BaseFragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    private var detail: AttractionsDetail? = null
    private val attractionsAdapter = AttractionDetailAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("detail", AttractionsDetail::class.java) as AttractionsDetail
        } else {
            arguments?.getSerializable("detail") as AttractionsDetail
        }
        (activity as AppCompatActivity).supportActionBar?.title = detail?.name
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root!!

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val layoutManager = CarouselLayoutManager()
            rvDetailImages.adapter = attractionsAdapter
            rvDetailImages.layoutManager = layoutManager
            detail?.let { detail ->
                if (detail.images.isNullOrEmpty()) {
                    rvDetailImages.visibility = View.GONE
                }
                attractionsAdapter.submitList(detail.images)
                tvDetailName.text = detail.name
                tvDetailDesc.text = detail.introduction
                tvDetailAddress.text = detail.address
                tvDetailUpdateTime.text = detail.modified
                tvDetailUrl.text = detail.url ?: detail.officialSite
                tvDetailUrl.hyperlinkStyle()
                tvDetailUrl.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("url", detail.url)
                    bundle.putString("title", detail.name)
                    view.findNavController().navigate(R.id.start_AttractionDetailWebView, bundle)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}