package com.example.gathr.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.gathr.entities.Reminder

class AlarmScheduler {

    fun createPendingIntent(context:Context , reminder: Reminder): PendingIntent?{
        val intent = Intent(context.applicationContext , AlarmReceiver::class.java).apply {
            type = reminder.id.toString()
            putExtra("id_title" , reminder.reminderTitle)
        }
        return PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
    }
}