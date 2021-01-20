package com.example.shang.user.ui.activity

import android.os.Bundle
import com.example.shang.user.R
import com.example.shang.user.injection.component.DaggerUserComponent
import com.example.shang.user.injection.module.UserModule
import com.example.shang.user.presenter.ResetPwdPresenter
import com.example.shang.user.presenter.view.ResetPwdView
import com.examples.shang.base.ext.enable
import com.examples.shang.base.ext.onClick
import com.examples.shang.base.ui.activity.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

/*
    重置密码界面
*/
class ResetPwdActivity : BaseMVPActivity<ResetPwdPresenter>(), ResetPwdView {
    private var pressTime:Long=0
    override fun injectComponent() {
        // 初始化一个Module，注入到this（也就是我们的activity中）
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }

    private fun initView() {
        //监听输入框
        mConfirmBtn.enable(mPwdEt, { isBtnEnable() })
        mConfirmBtn.enable(mPwdConfirmEt, { isBtnEnable() })
        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() !=mPwdConfirmEt.text.toString()){
               toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
        }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()

    }


    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

}



