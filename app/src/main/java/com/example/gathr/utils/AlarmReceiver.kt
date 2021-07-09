package com.example.gathr.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver(): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val title:String? = intent!!.extras!!.get("title") as String?

        if (title != null) {
            NotificationHelper.createNotification(context!!,title)
        }else{
            NotificationHelper.createNotification(context!!,"You have some work")
        }
    }
}

