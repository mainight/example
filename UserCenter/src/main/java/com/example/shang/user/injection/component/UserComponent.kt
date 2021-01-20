package com.example.shang.user.injection.component

import com.example.shang.user.injection.module.UserModule
import com.example.shang.user.ui.activity.*
import com.examples.shang.base.injection.PerComponentScope
import com.examples.shang.base.injection.component.ActivityComponent
import dagger.Component

/**  component 注入器，连接目标和依赖实例的桥梁，它必须是一个接口或者抽象类
 *   将需要的module传入进来
 *
 *   Component是dagger完成注入流程的组织者和协调者,依赖诉求方通过@Inject表达注入诉求后,Component负责调用"生产车间"生产实例,
 *   然后调用成员注入器完成对诉求方的注入
 *
 *   @PerComponentScope是自定义注解，注解会在class字节码文件中存在，在运行时获取
 */

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))// 在注册中添加依赖activity的component
interface UserComponent {
    // 指定注解到哪里，这里就是RegisterActivity ，inject方法可以将activity注入到ActivityComponent，通过该方法将activity中需要注入的对象注入到该activity中
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}