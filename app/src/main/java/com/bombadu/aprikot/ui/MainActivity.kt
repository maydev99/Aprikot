package com.bombadu.aprikot.ui

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.bombadu.aprikot.R
import com.bombadu.aprikot.network.NetworkUtil
import com.bombadu.aprikot.util.cancelNotifications
import com.bombadu.aprikot.util.sendNotification
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        
        
        if (NetworkUtil.checkNetConnectivity(this)) {
            NetworkUtil.showNoInternetDialog(this)
        }
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