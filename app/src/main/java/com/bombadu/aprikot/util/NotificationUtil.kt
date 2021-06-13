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
    context: Context,
    bodyText: String,
    channelId: String

) {

    val contentIntent = Intent(context, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val downloadImage = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.apricot250

    )

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(downloadImage)
        .bigLargeIcon(null)


    val builder = NotificationCompat.Builder(
        context,
        channelId
    ).setOnlyAlertOnce(true)

        .setSmallIcon(R.drawable.ic_stat_name)
        .setContentTitle(context.getString(R.string.aprikot_recipe_app))
        .setContentText(bodyText)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)

    notify(NOTIFICATION_ID, builder.build())


}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}