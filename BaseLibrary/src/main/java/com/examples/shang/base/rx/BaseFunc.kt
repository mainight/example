package com.examples.shang.base.rx

import android.util.Log
import com.examples.shang.base.common.ResultCode
import com.examples.shang.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * @author  Li Xuyang
 * date  2019/8/11 20:34
 */
class BaseFunc<T>:Func1<BaseResp<T>,Observable<T>>{

        override fun call(t: BaseResp<T>): Observable<T> {
            if (t.status != ResultCode.SUCCESS){
                return  Observable.error(BaseException(t.status,t.message))
            }else{
                return Observable.just(t.data)
            }
        }

}