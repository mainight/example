package com.examples.shang.base.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.hardware.Camera
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionUtil {
    const val CAMERA_PERMISSION_REQUEST_CODE = 52
    const val CAMERA_FOR_VIDEO_PERMISSION_REQUEST_CODE = 51
    const val CONTACTS_PERMISSION_REQUEST_CODE = 50
    const val LOCATION_PERMISSION_REQUEST_CODE = 53
    const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 54
    const val CALENDAR_WRITE_AND_READ_PERMISSION_REQUEST_CODE = 55
    const val CAMERA_FOR_VIDEO_CHATTING_PERMISSION_REQUEST_CODE = 56
    const val CAMERA_FOR_QRCODE_PERMISSION_REQUEST_CODE = 57
    const val CAMERA_FOR_PCODE_PERMISSION_REQUEST_CODE = 58
    const val READ_SMS_REQUEST_CODE = 59
    const val CHECK_CAMERA_AND_LOCATION_PERMISSION_BEFORE_TOFACE = 60
    const val JS_UPLOAD_FACE_PIC = 61
    const val CHECK_ACTIVITY_RECOGNITION_FOR_STEP_COUNT = 62
    const val IGNORE_BATTERY_REQUEST_CODE = 63
    fun checkPermission(activity: Activity?, permission: String, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val isGranted = ContextCompat.checkSelfPermission(activity!!, permission) == PackageManager.PERMISSION_GRANTED
            if (!isGranted) {
                ActivityCompat.requestPermissions(
                        activity, arrayOf(permission),
                        requestCode)
            }
            return isGranted
        }
        return true
    }

    fun checkPermissions(activity: Activity?, permissions: Array<String?>, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var isGranted = true
            for (i in permissions.indices) {
                isGranted = isGranted and (ContextCompat.checkSelfPermission(activity!!, permissions[i]!!) == PackageManager.PERMISSION_GRANTED)
            }
            if (!isGranted) {
                ActivityCompat.requestPermissions(
                        activity!!,
                        permissions,
                        requestCode)
            }
            return isGranted
        }
        return true
    }

    fun checkPermissonForFragment(fragment: Fragment, permission: String, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val isGanted = ContextCompat.checkSelfPermission(fragment.activity!!, permission) == PackageManager.PERMISSION_GRANTED
            if (!isGanted) {
                fragment.requestPermissions(arrayOf(permission), requestCode)
            }
            return isGanted
        }
        return true
    }

    fun checkPermissionsForFragment(fragment: Fragment, permissions: Array<String?>, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var isGranted = true
            for (i in permissions.indices) {
                isGranted = isGranted and (ContextCompat.checkSelfPermission(fragment.activity!!, permissions[i]!!) == PackageManager.PERMISSION_GRANTED)
            }
            if (!isGranted) {
                fragment.requestPermissions(permissions, requestCode)
            }
            return isGranted
        }
        return true
    }

    fun ishasVoicePermission(): Boolean { //判断麦克风权限
        return try {
            val record = AudioRecord(MediaRecorder.AudioSource.MIC, 22050, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, AudioRecord.getMinBufferSize(22050, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT))
            //开始录制音频
            try {
                // 防止某些手机崩溃，例如联想
                record.startRecording()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
            val recordingState = record.recordingState
            if (recordingState == AudioRecord.RECORDSTATE_STOPPED) {
                return false
            }
            record.release()
            true
        } catch (e: Exception) {
            false
        }
    }
}
