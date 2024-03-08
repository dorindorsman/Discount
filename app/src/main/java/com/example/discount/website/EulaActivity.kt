package com.example.discount.website

import android.app.Activity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.discount.R

class EulaActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eula)
        var url = intent.getStringExtra(EXTRA_URL)
        if (url == null) {
            url = "file:///android_asset/" + getString(R.string.eula_filename)
        }
        val webView = findViewById<WebView>(R.id.webView1)
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.setSupportZoom(true)
        webView.settings.displayZoomControls = false
        webView.settings.useWideViewPort = true
        webView.settings.textZoom = 70
        webView.settings.loadWithOverviewMode = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return false
            }
        }
        webView.loadUrl(url)
    }

    companion object {
        const val EXTRA_URL = "EXTRA_URL"
    }
}