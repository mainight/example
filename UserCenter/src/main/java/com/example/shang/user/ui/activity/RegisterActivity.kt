package com.example.shang.user.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.shang.user.R
import com.example.shang.user.injection.component.DaggerUserComponent
import com.example.shang.user.injection.module.UserModule
import com.example.shang.user.presenter.RegisterPresenter
import com.example.shang.user.presenter.view.RegisterView
import com.examples.shang.base.common.AppManager
import com.examples.shang.base.ext.enable
import com.examples.shang.base.ext.onClick
import com.examples.shang.base.ui.activity.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
/*
    注册界面
*/
class RegisterActivity : BaseMVPActivity<RegisterPresenter>(),RegisterView,View.OnClickListener {
    private var pressTime:Long=0
    override fun injectComponent() {
        // 初始化一个Module，注入到this（也就是我们的activity中）
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this

    }
    /*
       注册回调
     */
    override fun onRegisterResult(result: String ) {
            Log.d("zzz","onRegisterResult")
            toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // 直接用xml的id，无需再去初始化控件，这种是kotlin-android-extensions的用法
        initView()
     /* mRegisterBtn.onClick {
          mPresenter.register(mPhoneEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
      }*/

    }

    private fun initView() {
        //监听输入框
        mRegisterBtn.enable(mMobileEt, { isBtnEnable() })
        mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mGetVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)

//        mGetVerifyCodeBtn.onClick {
//            mGetVerifyCodeBtn.requestSendVerifyNumber()
//        }
//
//        mRegisterBtn.onClick(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                mPresenter.register(mPwdEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
//            }
//        })
    }
    override fun onBackPressed() {
        //实现点击两次退出程序
           val time=System.currentTimeMillis()
           if (time-pressTime>2000){
               toast("再按一次退出程序")
               pressTime=time
           }else{
               AppManager.instance.exitApp(this)
           }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                 R.id.mGetVerifyCodeBtn->{
                     mGetVerifyCodeBtn.requestSendVerifyNumber()
                     toast("发送验证码")
                 }

                R.id.mRegisterBtn->{
                    mPresenter.register(mPwdEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
                }
            }
        }
    }
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()

    }

}



