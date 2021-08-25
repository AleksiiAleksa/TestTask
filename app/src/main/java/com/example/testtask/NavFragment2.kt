package com.example.testtask

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_nav2.*

class NavFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nav2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = "https://github.com/${getArguments()?.getString("full_name")}"
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url)

        shareButton.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "type/palin"
            val shareBody = "Sending the link"
            val shareSub = url
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(myIntent, "Share your app"))
        }
    }
}