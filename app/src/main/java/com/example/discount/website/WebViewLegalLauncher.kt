package com.example.discount.website

import android.content.Context
import android.content.Intent

class WebViewLegalLauncher(
    private var mContext: Context
){

    fun startEulaActivity(url: String) {
        val intent = Intent(mContext, EulaActivity::class.java)
        intent.putExtra(EulaActivity.EXTRA_URL, url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
    }
}