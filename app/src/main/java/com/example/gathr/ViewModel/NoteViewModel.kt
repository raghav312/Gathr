package com.example.gathr.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gathr.repositories.NotesRepository
import com.example.gathr.db.NotesDatabase
import com.example.gathr.entities.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//ViewModel provides separation of concern.The ui will only contact concerning viewmodel to make and observe changes.
//ViewModel interacrts with repository which further fetch or changes data in the Room

//This is ViewModel is concerned with the Bulletin functionality.
//It includes 3 functions which are insert, delete and query of fetch all data.
//Coroutines is used run which these function on IO thread providing a lag free experience

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val repo : NotesRepository
    val allNotes:LiveData<List<Note>>

    init{
        //initialize these on every instance fetch call
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repo = NotesRepository(dao)
        allNotes = repo.allNotes
    }

    //Runs on IO thread , when called by usig Co-routines
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }

}