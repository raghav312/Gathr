package com.example.gathr.repositories

import androidx.lifecycle.LiveData
import com.example.gathr.daos.ReminderDao
import com.example.gathr.entities.Reminder


//Same use as notes repository
class ReminderRepository(private val reminderDao: ReminderDao) {

    val allReminders:LiveData<List<Reminder>> = reminderDao.getAllReminders()

    //Made into suspend so that there is no lag on UI thread
    //All these functions run on IO thread
    suspend fun insertReminder(reminder: Reminder){
        reminderDao.insertReminder(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder){
        reminderDao.deleteReminder(reminder)
    }

    suspend fun changeActive(reminder: Reminder){
        reminderDao.changeActive(reminder)
    }

}