package com.example.shang.user.presenter

import android.util.Log
import com.example.shang.user.data.protocol.UserInfo
import com.example.shang.user.presenter.view.LoginView
import com.example.shang.user.presenter.view.RegisterView
import com.example.shang.user.presenter.view.UserInfoView
import com.example.shang.user.service.UserService
import com.example.shang.user.service.impl.UserServiceImpl
import com.examples.shang.base.ext.execute
import com.examples.shang.base.presenter.BasePresenter
import com.examples.shang.base.rx.BaseSubscriber
import com.kotlin.base.utils.NetWorkUtils
import javax.inject.Inject
import javax.inject.Named

class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService:UserService


}