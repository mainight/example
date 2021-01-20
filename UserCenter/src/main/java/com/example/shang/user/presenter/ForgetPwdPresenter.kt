package com.example.shang.user.presenter

import android.util.Log
import com.example.shang.user.presenter.view.ForgetPwdView
import com.example.shang.user.presenter.view.RegisterView
import com.example.shang.user.presenter.view.ResetPwdView
import com.example.shang.user.service.UserService
import com.example.shang.user.service.impl.UserServiceImpl
import com.examples.shang.base.ext.execute
import com.examples.shang.base.presenter.BasePresenter
import com.examples.shang.base.rx.BaseSubscriber
import com.kotlin.base.utils.NetWorkUtils
import javax.inject.Inject
import javax.inject.Named

class ForgetPwdPresenter @Inject constructor(): BasePresenter<ForgetPwdView>() {

     //@field:[Named ("service")] // 这种写法是才是kotlin的，java中可以直接用@Named("service")
    @Inject
    lateinit var userService:UserService
    fun forgetPwd(phone:String,verifyCode:String){
        /**
         * 业务逻辑
         */
        if (!checkNetWork()) {
            return
        }
      mView.showLoading()
      //val userService = UserServiceImpl()
      userService.forgetPwd(phone,verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                            mView.onForgetPwdResult("验证成功")
                    }

                }, lifecycleProvider)
    }

}