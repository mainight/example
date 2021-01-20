package com.examples.shang.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope


// 自定义Scope，每个模块都有作用域了 注解类
@Scope
@Documented
@Retention(RUNTIME)
annotation class  PerComponentScope