package com.example.gathr.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gathr.daos.ReminderDao
import com.example.gathr.entities.Reminder

//database is wrapper on dao + entities

//database collection
@Database(entities = arrayOf(Reminder::class) , version = 1 , exportSchema = false)
abstract class ReminderDatabase:RoomDatabase() {
    abstract fun getReminderDao(): ReminderDao

    companion object{
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getDatabase(context: Context): ReminderDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDatabase::class.java,
                    "reminder_database").build()

                INSTANCE = instance
                instance
            }
        }
    }
}