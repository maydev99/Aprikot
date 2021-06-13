package com.bombadu.aprikot.util

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.bombadu.aprikot.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {


        if (intent.action == "ALARM_ACTION") {
            val message = intent.getStringExtra("ALARM_STRING").toString()
            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager


            notificationManager.sendNotification(
                context, message, context.getString(R.string.notification_channel_id))

        }

    }
}