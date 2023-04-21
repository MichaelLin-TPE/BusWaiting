package com.bus.buswaiting.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bus.buswaiting.MainActivity
import com.bus.buswaiting.R
import com.bus.buswaiting.tool.Tool
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
         Tool.logLongMessage("Michael", "Refreshed token: $token")
        // 將新的註冊令牌發送到您的應用服務器以進行後續處理
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
         Tool.logLongMessage("Michael", "From: " + remoteMessage.from)
        if (remoteMessage.data.isNotEmpty()) {
             Tool.logLongMessage("Michael", "Message data payload: " + remoteMessage.data)
        }
        if (remoteMessage.notification != null) {
             Tool.logLongMessage(
                "Michael", "Message Notification Body: " + remoteMessage.notification!!
                    .body
            )
            sendNotification(remoteMessage.notification!!.body)
        }
    }

    private fun sendNotification(messageBody: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0 /* Request code */,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // 添加全屏幕意圖
        val fullScreenIntent = PendingIntent.getActivity(
            this,
            0 /* Request code */,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "Channel_ID"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.mipmap.app_logo_round)
            setContentTitle(getString(R.string.app_name))
            setContentText(messageBody)
            setAutoCancel(true)
            setSound(defaultSoundUri)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setDefaults(NotificationCompat.DEFAULT_ALL)
            setContentIntent(pendingIntent)
            setFullScreenIntent(fullScreenIntent, true) // 添加全屏幕意圖
        }

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Since Android O (API 26), it is required to specify a notification channel for notifications.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

}