package com.example.shang.user.ui.activity

import android.os.Bundle
import android.view.View
import com.example.shang.user.R
import com.example.shang.user.data.protocol.UserInfo
import com.example.shang.user.injection.component.DaggerUserComponent
import com.example.shang.user.injection.module.UserModule
import com.example.shang.user.presenter.LoginPresenter
import com.example.shang.user.presenter.view.LoginView
import com.examples.shang.base.ext.enable
import com.examples.shang.base.ext.onClick
import com.examples.shang.base.ui.activity.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.mMobileEt
import kotlinx.android.synthetic.main.activity_register.mPwdEt
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
    登陆界面
*/
class LoginActivity : BaseMVPActivity<LoginPresenter>(),LoginView,View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 直接用xml的id，无需再去初始化控件，这种是kotlin-android-extensions的用法
        initView()
    }
    private fun initView() {
        //监听输入框
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })
        mLoginBtn.onClick(this)
        //虽然login中有headerBar可以引用headerBar，但是不能通过扩展访问HeaderBar里面的内容，即不能之间mheaderBar.onclick（）会报错
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)

    }
    override fun injectComponent() {
        // 初始化一个Module，注入到this（也就是我们的activity中）
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onClick(v: View) {
            when(v.id){
                 R.id.mRightTv->{
                     startActivity<RegisterActivity>()
                 }
                 R.id.mLoginBtn->{
                     mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
                 }
                 R.id.mForgetPwdTv->{
                    startActivity<ForgetPwdActivity>()
                 }
            }
        }
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()


    }

    override fun onLoginResult(result: UserInfo) {
        toast("登陆成功")
        startActivity<UserInfoActivity>()
    }

}



