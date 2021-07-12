package com.example.gathr.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gathr.entities.Reminder

//Data Access Object are classes which interacts with tables
//using SQL querires
@Dao
interface ReminderDao {
    //Insert query
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    //Delete query
    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    //Query to get all the entities
    @Query("SELECT * FROM reminders_table order by id ASC")
    fun getAllReminders(): LiveData<List<Reminder>>

    //Update query to change the status
    @Update
    suspend fun changeActive(reminder:Reminder)
}