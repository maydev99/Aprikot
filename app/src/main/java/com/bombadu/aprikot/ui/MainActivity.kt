package com.bombadu.aprikot.ui

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.bombadu.aprikot.R
import com.bombadu.aprikot.util.AlarmReceiver
import com.bombadu.aprikot.util.cancelNotifications
import com.bombadu.aprikot.util.sendNotification
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
        createChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name)
        )

        val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val wakeOn = sharedPrefs.getBoolean("is_sleep_on", false)
        val notificationManager = this.getSystemService(
            NotificationManager::class.java) as NotificationManager
        if (wakeOn) {
            notificationManager.sendNotification(applicationContext, getString(R.string.wake_lock_is_on))
        } else {
            notificationManager.cancelNotifications()
        }

        //Alarm for Notification

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.action = "ALARM_ACTION"
        intent.putExtra("ALARM_STRING", getString(R.string.getting_hungry))

        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        //Alarm Time
        val seconds = 1000L
        val minutes = seconds * 60
        val hours = minutes * 60
        //val days = hours * 24
        val trigger = 5 * seconds
        val interval = 2 * hours

        //val alarmTimeAtUTC = System.currentTimeMillis() + trigger
        val initialTrigger = System.currentTimeMillis() + trigger

        //Set alarm service
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, initialTrigger, interval, pendingIntent)

    }



    private fun setupNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }


    private fun Context.createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(false)

                enableLights(true)
                lightColor = Color.BLUE
                enableVibration(true)
                description = getString(R.string.notification_description)
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


}