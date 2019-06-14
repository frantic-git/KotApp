package ru.frantic.kotapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class BrowserActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        val webView : WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        val data = intent.data
        webView.loadUrl(data?.toString())
    }
}
