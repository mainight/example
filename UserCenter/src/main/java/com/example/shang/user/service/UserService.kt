package com.example.shang.user.service

import com.example.shang.user.data.protocol.UserInfo
import rx.Observable
/*
    业务层
 */
// 当userService有两个实现类时，为了区别使用哪个，就需要@qualifier
//retrofit首先创建接口，即程序中需要什么请求操作
interface UserService {
    fun register(phone:String,verifyCode:String,pwd:String): Observable<Boolean>

    fun login(phone:String,pwd:String,pushId:String): Observable<UserInfo>

    fun forgetPwd(phone:String,verifyCOde:String): Observable<Boolean>

    fun resetPwd(phone:String,pwd:String): Observable<Boolean>

}