package com.example.gathr.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gathr.entities.Note


//Data Access Object are classes which interacts with tables
//using SQL querires
@Dao
interface NotesDao {

    //Insert query
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    //Delete query
    @Delete
    suspend fun delete(note:Note)

    //Query to get all the entities
    @Query("SELECT * FROM notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}