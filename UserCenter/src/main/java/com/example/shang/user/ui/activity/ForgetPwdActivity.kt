package com.example.shang.user.ui.activity

import android.os.Bundle
import android.view.View
import com.example.shang.user.R
import com.example.shang.user.injection.component.DaggerUserComponent
import com.example.shang.user.injection.module.UserModule
import com.example.shang.user.presenter.ForgetPwdPresenter
import com.example.shang.user.presenter.view.ForgetPwdView
import com.examples.shang.base.ext.enable
import com.examples.shang.base.ext.onClick
import com.examples.shang.base.ui.activity.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.activity_register.mMobileEt
import kotlinx.android.synthetic.main.activity_register.mVerifyCodeEt
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
/*
    忘记密码界面
*/
class ForgetPwdActivity : BaseMVPActivity<ForgetPwdPresenter>(),ForgetPwdView,View.OnClickListener {
    private var pressTime:Long=0
    override fun injectComponent() {
        // 初始化一个Module，注入到this（也就是我们的activity中）
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    private fun initView() {
        //监听输入框
        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                 R.id.mVerifyCodeBtn->{
                     mVerifyCodeBtn.requestSendVerifyNumber()
                     toast("发送验证码")
                 }

                R.id.mRegisterBtn->{
                    mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
                }
            }
        }
    }
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()

    }
    /*
       注册回调
     */
    override fun onForgetPwdResult(result: String) {
       toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }

}



