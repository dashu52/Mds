package com.mds.wanandroid.ui.information.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.View.VISIBLE
import android.webkit.WebSettings.LOAD_NO_CACHE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.mds.wanandroid.R
import com.mds.wanandroid.base.BaseActivity
import com.mds.wanandroid.mvp.contract.ArticleContract
import com.mds.wanandroid.mvp.presenter.ArticlePresenter
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**

@author duanjianlin
@description:
@date : 19/09/20 17:15
 */
class ArticleActivity: BaseActivity<ArticleContract.IView, ArticleContract.IPersenter>(), View.OnClickListener {


    private var webview: WebView? = null

    private var toolbar: Toolbar? = null


    private var mProgressBar: ProgressBar? = null


    private var mUrl = ""   //链接地址

    private var mTitle = ""

    private var ivToolbarLeft: ImageView? = null

    private var tvTitle: AppCompatTextView? = null

    private var ivShare: ImageView? = null

    override fun getLayout(): Int {
        return R.layout.activity_article_detail
    }

    override fun initView() {
        ivShare = findViewById(R.id.iv_share)
        webview = findViewById(R.id.webview)
        ivToolbarLeft = findViewById(R.id.iv_toolbar_left)
        tvTitle = findViewById(R.id.tv_title)
        toolbar = findViewById(R.id.toolbar)

        ivShare?.setOnClickListener(this)
        ivToolbarLeft?.setOnClickListener(this)
        mProgressBar = ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal)
        val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 8)
        mProgressBar?.setLayoutParams(layoutParams)
        webview?.addView(mProgressBar)
    }

    override fun createPresenter(): ArticleContract.IPersenter {
        return ArticlePresenter()
    }

    override fun initData(savedInstanceState: Bundle?) {
        initIntent()
        initWebView()
    }
    private fun initIntent() {
        val intent = getIntent()
        if (intent != null) {
            var tempTitle = intent?.getStringExtra("title")
            var tempUrl = intent?.getStringExtra("url")
            if(tempTitle!=null){
                mTitle = tempTitle
            }
            if(tempUrl!=null){
                mUrl = tempUrl
            }
        }
    }


    /**
     * 初始化webview并加载URL
     */
    private fun initWebView() {

        webview?.getSettings()?.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webview?.getSettings()?.builtInZoomControls = false //设置内置的缩放控件。若为false，则该WebView不可缩放
        webview?.getSettings()?.displayZoomControls = true //隐藏原生的缩放控件
        webview?.getSettings()?.blockNetworkImage = false//解决图片不显示
        webview?.getSettings()?.loadsImagesAutomatically = true //支持自动加载图片
        webview?.getSettings()?.defaultTextEncodingName = "utf-8"//设置编码格式
        webview?.getSettings()?.cacheMode = LOAD_NO_CACHE
        webview?.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }
        })

        // 获取网页加载进度
        webview?.setWebChromeClient(object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    tvTitle?.setText(view!!.title)
                    mProgressBar?.setVisibility(View.GONE)
                } else {

                    if (mProgressBar?.getVisibility() == View.GONE)
                        mProgressBar?.setVisibility(VISIBLE)
                    mProgressBar?.setProgress(newProgress)
                    tvTitle?.setText("正在加载....")
                }
            }
        })

        webview?.getSettings()?.javaScriptEnabled = true

        webview?.loadUrl(mUrl)

    }
    override fun onClick(v: View?) {
        if (v?.getId() == R.id.iv_toolbar_left) {
            finish()
        }

        if (v?.getId() == R.id.iv_share) {
            val textIntent = Intent(Intent.ACTION_SEND)
            textIntent.type = "text/plain"
            textIntent.putExtra(Intent.EXTRA_TEXT, mUrl)
            startActivity(Intent.createChooser(textIntent, mTitle))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.webview != null) {
            webview?.clearCache(true)
            webview?.clearHistory()
            //((ViewGroup)webview.getParent()).removeView(webview);
            webview?.destroy()
        }
    }
}