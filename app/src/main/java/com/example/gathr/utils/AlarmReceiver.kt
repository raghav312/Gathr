package com.example.gathr.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver(): BroadcastReceiver() {

    //listens to broadcast from pending request
    //listens only when alarm manager triggers it on given time
    override fun onReceive(context: Context?, intent: Intent?) {
        val title:String? = intent!!.extras!!.get("title") as String?

        //if extras are not null , create notif with that title
        //else with standard title
        if (title != null) {

            NotificationHelper.createNotification(context!!,title)
        }else{
            NotificationHelper.createNotification(context!!,"You have some work")
        }
    }
}

