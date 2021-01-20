package com.examples.shang.base.injection.module

import android.content.Context
import com.examples.shang.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/*
   AppComponent对应moudle，前者里面全是对象，后者是前者的实现
 */

@Module
class AppModule(private val context: BaseApplication) {
    //singleton没有特殊的作用，分清主次
    @Provides
    @Singleton
    fun providesContext():Context{
        return context;
    }
}