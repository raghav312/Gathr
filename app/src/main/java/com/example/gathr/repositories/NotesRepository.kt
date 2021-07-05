package com.example.gathr.repositories

import androidx.lifecycle.LiveData
import com.example.gathr.daos.NotesDao
import com.example.gathr.entities.Note

class NotesRepository(private val notesDao:NotesDao) {
    val allNotes :LiveData<List<Note>> = notesDao.getAllNotes()
    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
}