package com.examples.shang.base.rx

import android.util.Log
import com.examples.shang.base.presenter.view.BaseView
import rx.Subscriber

// 基础的订阅者
open class BaseSubscriber<T>(val baseView: BaseView):Subscriber<T>() {
    override fun onNext(t: T) {
        Log.d("zzz","onNext")
    }

    override fun onCompleted() {
      baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if(e is BaseException){
            baseView.onError(e.msg)
        }
    }
}