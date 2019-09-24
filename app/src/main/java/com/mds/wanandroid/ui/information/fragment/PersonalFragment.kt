package com.mds.wanandroid.ui.information.fragment

import android.arch.lifecycle.LifecycleOwner
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import com.mds.wanandroid.R
import com.mds.wanandroid.bean.CurrencyBean
import com.mds.wanandroid.mvp.contract.PersonalContract
import com.mds.wanandroid.mvp.presenter.PersonalPresenter
import com.mds.wanandroid.ui.information.activity.LoginActivity
import com.mds.wanandroid.ui.information.activity.ArticleActivity
import com.mds.wanandroid.ui.information.activity.AboutActivity
import com.mds.wanandroid.ui.information.activity.CollectActivity
import com.mds.wanandroid.utils.MyLogger
import com.mds.wanandroid.utils.SpUtils
import com.trello.rxlifecycle2.LifecycleTransformer

/**

@author duanjianlin
@description:
@date : 19/09/18 17:22
 */
class PersonalFragment : BaseFragment<PersonalContract.IView, PersonalContract.IPersenter>()
        ,PersonalContract.IView
        , OnClickListener{


    lateinit var ll_info:View
    lateinit var tv_login:View
    lateinit var ll_nickname:View
    lateinit var iv_login_out:View
    lateinit var ll_collect:View
    lateinit var ll_about:View
    lateinit var ll_my_qzs:View
    lateinit var tv_username: TextView
    override fun getTransformer(): LifecycleTransformer<Long> {
        return this.bindToLifecycle()
    }

    override fun loginOutSuccess(dataBean: CurrencyBean.DataBean?) {
        MyLogger.dLog().e("loginOutSuccess")
        SpUtils.SetConfigString("username","")
        initInfo()
    }

    override fun LoginOutFail() {
    }


    override fun createPresenter(): PersonalContract.IPersenter {
        return PersonalPresenter()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_personal_center
    }

    override fun initView() {
        ll_info = rootView.findViewById(R.id.ll_info)
        tv_login = rootView.findViewById(R.id.tv_login)
        ll_nickname = rootView.findViewById(R.id.ll_nickname)
        iv_login_out = rootView.findViewById(R.id.iv_login_out)
        tv_username= rootView.findViewById(R.id.tv_username)
        ll_collect= rootView.findViewById(R.id.ll_collect)
        ll_about= rootView.findViewById(R.id.ll_about)
        ll_my_qzs= rootView.findViewById(R.id.ll_my_qzs)
        tv_login.setOnClickListener(this)
        iv_login_out.setOnClickListener(this)
        ll_collect.setOnClickListener(this)
        ll_about.setOnClickListener(this)
        ll_my_qzs.setOnClickListener(this)
        initInfo()
    }


    private fun initInfo() {
        if (SpUtils.GetConfigString("username").equals("")) {
            ll_info.setVisibility(View.GONE)
            tv_login.setVisibility(View.VISIBLE)
            ll_nickname.setVisibility(View.GONE)
            iv_login_out.setVisibility(View.GONE)

        } else {
            ll_info.setVisibility(View.VISIBLE)
            ll_nickname.setVisibility(View.VISIBLE)
            tv_login.setVisibility(View.GONE)
            tv_username.setText(SpUtils.GetConfigString("username") + "")
            iv_login_out.setVisibility(View.VISIBLE)
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            tv_login.id->startLoginAct()
            iv_login_out.id->presenter.loginOut()
            ll_collect.id->collect()
            ll_about.id->about()
            ll_my_qzs.id->myblog()
        }
    }

    fun collect(){
        MyLogger.dLog().d("click collect")
        val it = Intent()
        val cn = activity?.let { ComponentName(activity, CollectActivity::class.java)}
        it.setComponent(cn)
        activity?.startActivity(it)
    }
    fun about(){
        val it = Intent()
        val cn = activity?.let { ComponentName(activity, AboutActivity::class.java)}
        it.setComponent(cn)
        activity?.startActivity(it)
    }
    fun myblog(){
        val it = Intent()
        val cn = activity?.let { ComponentName(activity, ArticleActivity::class.java)}
        it.setComponent(cn)
        it.putExtra("title", "大树的博客")
        it.putExtra("url", "http://qinzishuai.cn")
        activity?.startActivity(it)
    }

    fun startLoginAct(){
        val it = Intent()
        val cn = activity?.let { ComponentName(activity, LoginActivity::class.java)}
        it.setComponent(cn)
        activity?.startActivity(it)
    }
    companion object{
        @JvmStatic
        fun newInstance(): PersonalFragment {
            return PersonalFragment()
        }

    }
}