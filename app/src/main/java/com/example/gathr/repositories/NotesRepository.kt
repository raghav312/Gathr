package com.example.gathr.repositories

import androidx.lifecycle.LiveData
import com.example.gathr.daos.NotesDao
import com.example.gathr.entities.Note

//Repository gives and takes command from viewModel and provide changes to database
//helps in providing separation of concerns
class NotesRepository(private val notesDao:NotesDao) {
    val allNotes :LiveData<List<Note>> = notesDao.getAllNotes()

    //Saves time on UI thread by running these on IO thread
    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
}