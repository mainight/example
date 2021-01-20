package com.examples.shang.base.injection.component

import android.content.Context
import com.examples.shang.base.injection.ActivityScope
import com.examples.shang.base.injection.module.ActivityModule
import com.examples.shang.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component


// application的component只有一个，而activity的有多个，所以给它们之间建立依赖关系
/*
   Component是dagger完成注入流程的组织者和协调者,依赖诉求方通过@Inject表达注入诉求后,Component负责调用"生产车间"生产实例,
   然后调用成员注入器完成对诉求方的注入
 */
@ActivityScope
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class)
)
interface ActivityComponent {
    fun context():Context

    fun lifecycleProvider():LifecycleProvider<*>

}