package com.mds.wanandroid.ui.information.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mds.wanandroid.R
import com.mds.wanandroid.base.BaseActivity
import com.mds.wanandroid.mvp.contract.AboutContract
import com.mds.wanandroid.mvp.presenter.AboutPresenter
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity<AboutContract.IView,AboutContract.IPresenter>() {
    override fun getLayout(): Int {
        return R.layout.activity_about
    }

    override fun initView() {
    }

    override fun createPresenter(): AboutContract.IPresenter {
        return AboutPresenter()
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        iv_toolbar_left.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                finish();
            }
        })
    }
}

