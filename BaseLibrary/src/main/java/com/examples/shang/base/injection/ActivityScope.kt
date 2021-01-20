package com.examples.shang.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope


// 自定义Scope，这个是activity级别的
@Scope
@Documented
@Retention(RUNTIME)
annotation class  ActivityScope