package com.examples.shang.base.rx

import android.util.Log
import com.examples.shang.base.common.ResultCode
import com.examples.shang.base.data.protocol.BaseResp
import rx.functions.Func1
import rx.Observable


class BaseFuncBoolean<T> : Func1<BaseResp<T>,Observable<Boolean>> {
    override fun call(t:BaseResp<T>):Observable<Boolean>{
        Log.d("zzz",t.status.toString())
        if (t.status!= ResultCode.SUCCESS){
            return  Observable.error(BaseException(t.status,t.message))
        }else{
            return Observable.just(true)
        }
    }
}