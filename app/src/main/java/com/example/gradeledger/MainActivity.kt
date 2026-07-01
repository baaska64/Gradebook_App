package com.example.gradeledger // Make sure this matches your actual package name!

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the top Android title bar to make it look like a full app
        supportActionBar?.hide()

        webView = findViewById(R.id.webview)

        // Force links and redirects to open inside the app, not in Chrome
        webView.webViewClient = WebViewClient()
        // Required for JS alerts/modals to function properly
        webView.webChromeClient = WebChromeClient()

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // CRITICAL: This enables localStorage to work on Android
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true

        // Load your local HTML file
        webView.loadUrl("file:///android_asset/index.html")
    }

    // Ensure the Android "Back" button navigates web history rather than closing the app immediately
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}