package com.example.gathr.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gathr.daos.NotesDao
import com.example.gathr.entities.Note

//database is wrapper on dao + entities

//database creation
@Database(entities = arrayOf(Note::class) , version = 1 , exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object{

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        //function to access database
        fun getDatabase(context: Context): NotesDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database").build()

                    INSTANCE = instance
                    instance
            }
        }
    }
}