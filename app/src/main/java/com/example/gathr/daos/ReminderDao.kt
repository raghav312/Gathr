package com.example.gathr.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gathr.entities.Reminder
@Dao
interface ReminderDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Query("SELECT * FROM reminders_table order by id ASC")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Update
    suspend fun changeActive(reminder:Reminder)

}