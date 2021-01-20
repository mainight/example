package com.examples.shang.base.injection.module

import android.app.Activity
import com.examples.shang.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

// activity级别的module
@Module
class ActivityModule(private val activity: Activity) {
    //规定activity所对应的域为自定义的ActivityScope
    @Provides
    @ActivityScope
    fun providesActivity():Activity{
        return activity;
    }
}