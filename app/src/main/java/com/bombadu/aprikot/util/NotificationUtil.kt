package com.bombadu.aprikot.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.bombadu.aprikot.R
import com.bombadu.aprikot.ui.MainActivity

const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(
    applicationContext: Context,
    bodyText: String

) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val downloadImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.apricot250

    )

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(downloadImage)
        .bigLargeIcon(null)


    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    ).setOnlyAlertOnce(true)

        .setSmallIcon(R.drawable.ic_stat_name)
        .setContentTitle(applicationContext.getString(R.string.aprikot_recipe_app))
        .setContentText(bodyText)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)

    notify(NOTIFICATION_ID, builder.build())


}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}