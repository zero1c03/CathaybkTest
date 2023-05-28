package com.weber.cathaybktest.fragment.attractions

import android.app.Application
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.weber.cathaybktest.R
import com.weber.cathaybktest.databinding.FragmentAttractionsBinding
import com.weber.cathaybktest.fragment.BaseFragment
import com.weber.cathaybktest.utils.Constants
import com.weber.cathaybktest.utils.CustomDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AttractionsFragment : BaseFragment(), MenuProvider, OnSharedPreferenceChangeListener {

    private lateinit var application: Application
    private var _binding: FragmentAttractionsBinding? = null
    private val binding get() = _binding

    private val viewModel: AttractionsViewModel by viewModels()
    private val attractionsAdapter = AttractionsAdapter()
    private var getAttractionsJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        application = activity?.application!!
        _binding = FragmentAttractionsBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            rvAttractions.adapter =
                attractionsAdapter.withLoadStateFooter(AttractionsFooterAdapter {
                    attractionsAdapter.retry()
                })
            attractionsAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        showLoading(binding!!.flLoading, false)
                    }

                    is LoadState.Loading -> {
                        showLoading(binding!!.flLoading, true)
                    }

                    is LoadState.Error -> {
                        showLoading(binding!!.flLoading, false)
                    }
                }
            }

            val layoutManager = LinearLayoutManager(context)
            rvAttractions.layoutManager = layoutManager

            val dividerItemDecoration = CustomDividerItemDecoration(30)
            rvAttractions.addItemDecoration(dividerItemDecoration)

            srAttractionsRefresh.setOnRefreshListener {
                srAttractionsRefresh.isRefreshing = true
                getAttractions()
            }

            if (attractionsAdapter.itemCount == 0) {
                getAttractions()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Constants.getSharedPreference(application)
            .registerOnSharedPreferenceChangeListener(this@AttractionsFragment)
    }

    override fun onPause() {
        super.onPause()
        Constants.getSharedPreference(application)
            .unregisterOnSharedPreferenceChangeListener(this@AttractionsFragment)
    }

    private fun getAttractions() {
        getAttractionsJob?.cancel()
        getAttractionsJob = lifecycleScope.launch {
            val lang = Constants.getLangSharedPreferences(application)
            viewModel.getAllAttractions(lang).collect {
                binding?.srAttractionsRefresh?.isRefreshing = false
                attractionsAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        getAttractions()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_language, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {

            R.id.zh_tw -> {
                Constants.setLangSharedPreferences(application, "zh-tw")
            }

            R.id.zh_cn -> {
                Constants.setLangSharedPreferences(application, "zh-cn")
            }

            R.id.en -> {
                Constants.setLangSharedPreferences(application, "en")
            }

            R.id.ja -> {
                Constants.setLangSharedPreferences(application, "ja")
            }

            R.id.ko -> {
                Constants.setLangSharedPreferences(application, "ko")
            }

            R.id.es -> {
                Constants.setLangSharedPreferences(application, "es")
            }

            R.id.id -> {
                Constants.setLangSharedPreferences(application, "id")
            }

            R.id.th -> {
                Constants.setLangSharedPreferences(application, "th")
            }

            R.id.vi -> {
                Constants.setLangSharedPreferences(application, "vi")
            }
        }
        return true
    }


}