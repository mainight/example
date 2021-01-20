package com.example.shang.user.injection.module

import com.example.shang.user.service.UserService
import com.example.shang.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

// module可以看成是一个简单工厂，它提供实例化的方法，默认provides开头

@Module
class UserModule{

    //@Name是@qualifier的一种实现方式，为了解决注解迷失，多个注解不知道调用哪一个Rp
    @Provides
    fun providesUserService(service: UserServiceImpl):UserService{
        return service
    }


}