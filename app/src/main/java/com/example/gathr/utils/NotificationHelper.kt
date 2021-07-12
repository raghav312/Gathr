package com.example.gathr.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.gathr.MainActivity
import com.example.gathr.R

class NotificationHelper{

    //static function so that there is no need for making an instance
    companion object {

        //notif channel helps in getting the notification even when the app is closed
        //also does not need INTERNET connection
        // create a system notification channel with these parameters
        fun createNotificationChannel(
            context: Context,
            importance: Int,
            showBadge: Boolean,
            name: String,
            description: String
        ) {
            //Check build version to target device
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channelId = "${context.packageName}-$name"
                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description
                channel.setShowBadge(showBadge)

                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }
        }


        //create notification on these parameters
        fun createNotification(context: Context, title:String){
            val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
            val notificationBuilder = NotificationCompat.Builder(context,channelId).apply {
                //we can customise our notification
                //we can add icon , response button etc.
                setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
                setContentTitle(title)
                priority = NotificationCompat.PRIORITY_HIGH
                setAutoCancel(true)
                val intent = Intent(context , MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(context,0,intent,0)
                setContentIntent(pendingIntent)
            }

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(123,notificationBuilder.build())

        }
    }

}