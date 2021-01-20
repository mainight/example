package com.examples.shang.base.common

import android.app.Application
import android.content.Context
import com.examples.shang.base.injection.component.AppComponent
import com.examples.shang.base.injection.component.DaggerAppComponent
import com.examples.shang.base.injection.module.AppModule

class BaseApplication : Application() {

    // 不能定为private，因为activity要用到
    // app级别的component，提供一个函数，暴露出全局的context
    lateinit var AppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        // 初始化component
        initAppInjection()
        context=this
    }

    private fun initAppInjection() {
        //使用Dragger2完成依赖对象的创建
        AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build();
    }
    companion object{
       lateinit var context:Context
    }
}