package com.example.shang.user.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.shang.user.R
import com.example.shang.user.injection.component.DaggerUserComponent
import com.example.shang.user.injection.module.UserModule
import com.example.shang.user.presenter.UserInfoPresenter
import com.example.shang.user.presenter.view.UserInfoView
import com.examples.shang.base.ext.onClick
import com.examples.shang.base.ui.activity.BaseMVPActivity
import com.examples.shang.base.utils.DateUtils
import com.examples.shang.base.utils.PermissionUtil
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user_info.*
import java.io.File

/*
    用户信息
*/
class UserInfoActivity : BaseMVPActivity<UserInfoPresenter>(),UserInfoView ,TakePhoto.TakeResultListener{
    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile:File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto=TakePhotoImpl(this, this)
        initView()

        mTakePhoto.onCreate(savedInstanceState)
    }

    override fun injectComponent() {
        // 初始化一个Module，注入到this（也就是我们的activity中）
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this

    }

    private fun initView() {
        mUserIconView.onClick{
            showAlertView()
        }
    }
    /*
       弹出下框，选择头像获取方式
     */
    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when (position) {
                    0 -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (PermissionUtil.checkPermission(this@UserInfoActivity, Manifest.permission.CAMERA, PermissionUtil.CHECK_ACTIVITY_RECOGNITION_FOR_STEP_COUNT)) {
                                createTempFile()
                                mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                            }
                        } else {
                        }

                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }
        }
        ).show()
    }

    //申请权限结果返回处理
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionUtil.CHECK_ACTIVITY_RECOGNITION_FOR_STEP_COUNT -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                }
            }
        }
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("zzz", result?.image?.originalPath)
        Log.d("zzz", result?.image?.compressPath)
        Log.e("zzz", "successs")
    }

    override fun takeFail(result: TResult?, msg: String?) {

    }

    override fun takeCancel() {
       Log.e("zzz", "takephone")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }
    fun createTempFile(){
        val tempFileName= "${DateUtils.curTime}.png"
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            this.mTempFile=File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile=File(filesDir, tempFileName)
    }
}