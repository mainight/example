package com.examples.shang.base.widgets

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.example.shang.base.R
import org.jetbrains.anko.find

class ProgressLoading private constructor(context: Context,theme:Int): Dialog(context,theme) {
    companion object{
        private lateinit var mDialog:ProgressLoading
        private var animDrawable:AnimationDrawable? =null

        fun create(context: Context):ProgressLoading{
            mDialog= ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity=Gravity.CENTER

            val lp= mDialog.window.attributes
            lp.dimAmount=0.2f

            mDialog.window.attributes=lp

            val loadingView= mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable=loadingView.background as AnimationDrawable

            return mDialog

        }
    }

    /*
         显示加载对话框，动画开始
      */
    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    /*
        隐藏加载对话框，动画停止
     */
    fun hideLoading(){
        super.dismiss()
        animDrawable?.stop()
    }
}