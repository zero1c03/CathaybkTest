package com.weber.cathaybktest.fragment.detail

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.URLUtil
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.weber.cathaybktest.R
import com.weber.cathaybktest.databinding.WebDetailBinding
import com.weber.cathaybktest.fragment.BaseFragment

class AttractionDetailWebView : BaseFragment() {

    private var _binding: WebDetailBinding? = null
    private val binding get() = _binding!!

    private var url: String? = null
    private var title: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        url = arguments?.getString("url", "")
        title = arguments?.getString("title", "")
        _binding = WebDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = title
        initWebView()
        loadWebView()
    }

    private fun initWebView() {
        val webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                showLoading(binding.flLoading, false)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                AlertDialog.Builder(context).setMessage(R.string.webview_ssl_error)
                    .setPositiveButton(
                        R.string.yes
                    ) { dialog, which -> handler?.proceed() }
                    .setNegativeButton(
                        R.string.no
                    ) { dialog, which ->
                        super.onReceivedSslError(view, handler, error)
                    }
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false
                }

                // Otherwise allow the OS to handle things like tel, mailto, etc.
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    showSnackBar(binding.root, getString(R.string.webview_activity_not_found_error))
                }

                return true
            }
        }

        binding.apply {
            webView.webViewClient = webViewClient

            // 讓圖片初始fit螢幕
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = true

            // 開啟縮放
            webView.settings.builtInZoomControls = true
            webView.settings.displayZoomControls = false

            // 是否支持 Javascript
            webView.settings.javaScriptEnabled = true;
            webView.settings.allowFileAccess = true;
            webView.settings.allowContentAccess = true;
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    binding.root.findNavController().popBackStack()
                }
            }
            url?.let { webView.loadUrl(it) }
        }


    }


    private fun loadWebView() {
        if (!url.isNullOrEmpty() && URLUtil.isValidUrl(url)) {
            showLoading(binding.flLoading, true)
        } else {
            showSnackBar(binding.root, getString(R.string.webview_error))
        }
    }
}