package com.examples.shang.base.data.net

import com.examples.shang.base.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 工厂模式, 单例方式构造方法私有
/*
   使用retrofit发送一次请求，
   需要写一个接口
   然后定义一个接口方法
   然后构建retrofit
   然后调用create方法生成接口类
   然后调用enqueue或者execute方法发送一个异步或同步请求

   最后添加json解析器 如GsonConvertFactory来自动序列化json串，配置统一的cookie拦截器
   添加日志拦截器 retrofit不能拼接完整的url请求地址（完整的请求地址包括请求的主机地址，接口名，所有请求参数拼接，Retrofit最多只能看到接口，后面拼接的参数是看不到的）

 */
class RetrofitFactory private constructor(){

    companion object {
        //by lazy也是线程安全的
        val instance:RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor:Interceptor
    init {

        // 在初始化一个拦截器，用于指定头部的拦截
        interceptor = Interceptor {
            chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("charset","utf-8")
                    .build()
            chain.proceed(request)
        }
        //初始化retrofit client是okhttp的方法 Retrofit Post请求方式封装头部
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()

    }
    //日志拦截器。拦截时间和超时时间 传入两个拦截器
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor) // 自己初始化的拦截器，headers相关的
                .addInterceptor(initLoggingInterceptor())
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build()
    }
    //构造日志拦截器
    private fun initLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        //日志级别，打印的是body级别的日志
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    // 创建service 使用泛型
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }


}
