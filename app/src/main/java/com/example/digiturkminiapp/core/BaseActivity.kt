package com.example.digiturkminiapp.core

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.digiturkminiapp.R
import com.example.digiturkminiapp.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity: AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initToolbar()
        changeStatusBarColor()
    }

    private fun initToolbar() {
        if (mToolbar != null)
            setSupportActionBar(mToolbar)

        iv_page_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun changeStatusBarColor(){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.black, null)
        window.navigationBarColor = ResourcesCompat.getColor(resources,R.color.black, null)
    }

    var backButtonVisibility: Int
        get() = iv_page_back?.visibility ?: View.GONE
        set(value) {
            iv_page_back?.visibility = value
        }

    var toolbarTitle: String = ""
        get() = tv_app_title?.text.toString()
        set(value) {
            tv_app_title?.text = value
            field = value
        }

    var toolbarVisibility: Int = View.VISIBLE
        get() = mToolbar?.visibility ?: View.GONE
        set(value) {
            mToolbar?.visibility = value
            field = value
        }

    override fun onBackPressed() {
        window.decorView.hideKeyboard()
        super.onBackPressed()
    }

    fun showLoading(){
        progressDialog = ProgressDialog(this)
        if (!(this as Activity).isFinishing) {
            progressDialog?.show()
        }

        if (progressDialog?.window != null){
            progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        progressDialog?.setContentView(R.layout.progress_dialog)
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
    }

    fun hideLoading(){
        progressDialog?.let {
            if (progressDialog!!.isShowing){
                progressDialog!!.dismiss()
            }
        }
    }
}