package com.tobigaming.yeeter

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.tobigaming.yeeter.ui.theme.YeeterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YeeterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                webViewClient = MyWebViewClient()
                                webChromeClient = MyWebChromeClient()

                                // URL
                                loadUrl("https://yeeter.de.cool/home")
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    private class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            // Lasse alle URLs im WebView öffnen und nicht im externen Browser
            view?.loadUrl(request?.url.toString())
            return true
        }
    }

    private class MyWebChromeClient : WebChromeClient() {
        // Hier kannst du weitere Anpassungen für die WebChromeClient vornehmen
    }
}