package com.example.shang.user.data.api

import com.example.shang.user.data.protocol.*
import com.examples.shang.base.data.protocol.BaseResp
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable
/*
   网络层接口
*/
interface UserApi {

    /*@Body注解 作用于方法的参数 使用该注解定义的参数不可为null 当发送一个post请求或put请求，但是不想作为请求参数或表单的方式发送请求，
       使用该注解定义的参数可以直接传入一个实体类，retrofit会通过convert把该实体序列化并将序列化后的结果直接作为请求体发送出去
       ps序列化是将对象的状态转换为可保持或传输的格式的过程 把对象转换为字节序列的过程为对象的序列化 把字节序列恢复为对象的过程称为对象的反序列化
     */
    // 因为是retrofit，所以返回值就是一个Observable,它的类型是基础Resp<String> 提供注册功能的接口
    @POST("userCenter/register")
    fun register(@Body req:RegisterReq):Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req:LoginReq):Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req:ResetPwdReq):Observable<BaseResp<String>>

}