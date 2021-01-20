package com.example.shang.user.presenter.view

import com.example.shang.user.data.protocol.UserInfo
import com.examples.shang.base.presenter.view.BaseView
/*
   回调
 */
interface ResetPwdView:BaseView {

    fun onResetPwdResult(result:String)
}