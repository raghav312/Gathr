package com.example.gathr.repositories

import androidx.lifecycle.LiveData
import com.example.gathr.daos.ReminderDao
import com.example.gathr.entities.Reminder

class ReminderRepository(private val reminderDao: ReminderDao) {

    val allReminders:LiveData<List<Reminder>> = reminderDao.getAllReminders()

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