package com.yasincidem.pushnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFCMService : FirebaseMessagingService() {

    private lateinit var notificationManager: NotificationManager

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        val notification = remoteMessage!!.notification
        Log.d("FROM", remoteMessage.from)
        Handler(Looper.getMainLooper()).post({ Toast.makeText(this, remoteMessage.from, Toast.LENGTH_LONG).show() })
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel( "notificationID", "fcm", NotificationManager.IMPORTANCE_DEFAULT)
            channel.apply{
                description = "A sample of notification in OREO"
                setShowBadge(true)
                canShowBadge()
                enableLights(true)
                lightColor = Color.GRAY
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200)
            }
            notificationManager.createNotificationChannel(channel)
        }else {
            val notificationBuilder = NotificationCompat.Builder(this, "notification")
                    .setContentTitle(notification?.title)
                    .setContentText(notification?.body)
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent)
                    .setContentInfo("Content Info")
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                    .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setSmallIcon(applicationInfo.icon)

            notificationManager.notify(1, notificationBuilder.build())


        }



    }
}
