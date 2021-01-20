package com.example.shang.user.service.impl

import android.util.Log
import com.example.shang.user.data.protocol.UserInfo
import com.example.shang.user.data.repository.UserRepository
import com.example.shang.user.service.UserService
import com.examples.shang.base.ext.convert
import com.examples.shang.base.ext.convertBoolean
import com.examples.shang.base.rx.BaseFuncBoolean
import rx.Observable
import javax.inject.Inject
/*
    Service实现类
 */
class UserServiceImpl @Inject constructor():UserService {
    //实现访问网络的类UserReposotory
    @Inject
    lateinit var repository:UserRepository
     //注册
    override fun register(phone: String, pwd: String, verifyCode: String): Observable<Boolean> {
        //return repository.register(phone,pwd,verifyCode).convertBoolean()
              //.flatMap(BaseFuncBoolean()) // 直接返回true,测试*/
        return Observable.just(true)
    }
    //登陆
    override fun login(phone: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(phone,pwd,pushId).convert()
    }
    //忘记密码
    override fun forgetPwd(phone: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(phone,verifyCode).convertBoolean()
    }
    //重置密码
    override fun resetPwd(phone: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(phone,pwd).convertBoolean()
    }


}