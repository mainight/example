package com.examples.shang.base.ui.activity

import android.os.Bundle
import com.examples.shang.base.common.BaseApplication
import com.examples.shang.base.injection.component.ActivityComponent
import com.examples.shang.base.injection.component.DaggerActivityComponent
import com.examples.shang.base.injection.module.ActivityModule
import com.examples.shang.base.injection.module.LifecycleProviderModule
import com.examples.shang.base.presenter.BasePresenter
import com.examples.shang.base.presenter.view.BaseView
import org.jetbrains.anko.toast
import javax.inject.Inject

open abstract class BaseMVPActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(text:String) {
       toast(text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }
    abstract fun injectComponent()
    private fun initActivityInjection() {
        //as强制转换
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).AppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

}