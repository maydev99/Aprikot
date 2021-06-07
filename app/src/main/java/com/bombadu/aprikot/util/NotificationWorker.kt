package com.bombadu.aprikot.util

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bombadu.aprikot.R

class NotificationWorker(appContext: Context, params: WorkerParameters):
CoroutineWorker(appContext, params){


    companion object {
        const val WORK_NAME = "SendNotification"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun doWork(): Result {

        return try {
            val notificationManager = applicationContext.getSystemService(
                NotificationManager::class.java
            )

            notificationManager.sendNotification(applicationContext, applicationContext.getString(R.string.getting_hungry))
            Result.success()
        }catch (e: Exception) {
            Result.retry()
        }

    }


}