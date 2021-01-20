package com.examples.shang.base.injection.component

import android.content.Context
import com.examples.shang.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton
//singleton只是表示主次层次没有特别的作用，顶级的appcompose里面有appmoulde提供一个activity方法
/*
  AppComponent生命周期和Application一样的组件，可注入到自定义的Application类中，
  component都是接口，dragger2会生成对应的实现类 写了AppComponent就会有DaggerAppComponent实现类，需要编译才会出现
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent{ // app级别的component，提供一个函数，暴露出全局的context
    fun context() : Context
}