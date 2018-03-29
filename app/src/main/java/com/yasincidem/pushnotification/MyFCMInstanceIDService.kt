package com.yasincidem.pushnotification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFCMInstanceIDService : FirebaseInstanceIdService() {
    private val TAG = this.javaClass.simpleName

    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed Token : " + refreshedToken!!)
        super.onTokenRefresh()
    }
}
