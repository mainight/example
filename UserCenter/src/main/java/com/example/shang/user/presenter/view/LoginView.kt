package com.example.shang.user.presenter.view

import com.example.shang.user.data.protocol.UserInfo
import com.examples.shang.base.presenter.view.BaseView

interface LoginView:BaseView {

    fun onLoginResult(result:UserInfo )
}