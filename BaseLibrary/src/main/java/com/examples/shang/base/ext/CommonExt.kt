package com.examples.shang.base.ext

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.examples.shang.base.data.protocol.BaseResp
import com.examples.shang.base.rx.BaseFunc
import com.examples.shang.base.rx.BaseFuncBoolean
import com.examples.shang.base.rx.BaseSubscriber
import com.examples.shang.base.utils.DefaultTextWacher
import com.trello.rxlifecycle.LifecycleProvider
import okhttp3.internal.Util
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

// 通用扩展方法类


// 扩展Observable的一个函数，名字为execute 所有的函数都是基于observable
fun <T> Observable<T>.execute(subscriber : BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>){
    Log.d("zzz","execute "+this.toString())
    this.observeOn(AndroidSchedulers.mainThread()) // 订阅
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}
fun <T> Observable<BaseResp<T>>.convert(): Observable<T>{
    return  this.flatMap(BaseFunc())
}
fun <T>Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener:View.OnClickListener){
    this.setOnClickListener(listener)
}
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

fun Button.enable(et:EditText,method: () -> Boolean){
    val btn=this
    et.addTextChangedListener(object:DefaultTextWacher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled=method()
        }
    })
}