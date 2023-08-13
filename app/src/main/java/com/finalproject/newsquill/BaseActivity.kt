package com.finalproject.newsquill

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {

    private lateinit var progressBar: Dialog

    override fun onPause() {
        super.onPause()
        hideProgressBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressBar()
    }

    fun showProgressBar() {
        progressBar = Dialog(this)
        progressBar.setContentView(R.layout.progress_bar)
        progressBar.setCancelable(false)
        progressBar.show()
    }

    fun hideProgressBar() {
        if (::progressBar.isInitialized && progressBar.isShowing) {
            progressBar.dismiss()
        }
    }

    fun showToast(activity: Activity, message:String){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}
