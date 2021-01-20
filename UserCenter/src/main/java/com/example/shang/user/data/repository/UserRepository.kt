package com.example.shang.user.data.repository

import android.util.Log
import com.example.shang.user.data.api.UserApi
import com.example.shang.user.data.protocol.*
import com.examples.shang.base.data.net.RetrofitFactory
import com.examples.shang.base.data.protocol.BaseResp
import rx.Observable
import javax.inject.Inject
/*
   数据层
 */
// 真正访问网络的类 封装数据层
class UserRepository @Inject constructor() {
    fun register(phone:String,pwd:String,verifyCode:String):Observable<BaseResp<String>>{
        Log.d("zzz","UserRepository")
        //直接访问网络，访问数据，调用的是工厂， 使用工厂模式创建一个单例，创建一个方法，注册方法传入注册参数 ，接收register的响应
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(phone, pwd, verifyCode))
    }
    fun login(phone:String,pwd:String,pushId:String):Observable<BaseResp<UserInfo>>{
        //直接访问网络，访问数据，调用的是工厂， 使用工厂模式创建一个单例，
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(phone, pwd, pushId))
    }
    fun forgetPwd(phone:String,verifyCode:String):Observable<BaseResp<String>>{
        //直接访问网络，访问数据，调用的是工厂， 使用工厂模式创建一个单例，
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(phone, verifyCode))
    }
    //对应的方法是resetPwd 请求ResetPwdRwq
    fun resetPwd(phone:String,pwd:String):Observable<BaseResp<String>>{
        //直接访问网络，访问数据，调用的是工厂， 使用工厂模式创建一个单例，
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(ResetPwdReq(phone, pwd))
    }
}