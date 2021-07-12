package com.example.gathr.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gathr.db.ReminderDatabase
import com.example.gathr.entities.Reminder
import com.example.gathr.repositories.ReminderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//ViewModel provides separation of concern.The ui will only contact concerning viewmodel to make and observe changes.
//ViewModel interacrts with repository which further fetch or changes data in the Room

//This is ViewModel is concerned with the Bulletin functionality.
//It includes 3 functions which are insert, delete and query of fetch all data.
//Coroutines is used run which these function on IO thread providing a lag free experience


class ReminderViewModel(application: Application):AndroidViewModel(application) {
    private val repo: ReminderRepository
    val allReminders:LiveData<List<Reminder>>


    init{
        val dao = ReminderDatabase.getDatabase(application).getReminderDao()
        repo = ReminderRepository(dao)
        allReminders = repo.allReminders
    }

    //runs these function on IO thread using Coroutines
    fun deleteReminder(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteReminder(reminder)
    }

    fun insertReminder(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertReminder(reminder)
    }

    fun changeActive(reminder: Reminder)= viewModelScope.launch(Dispatchers.IO){
        repo.changeActive(reminder)
    }

}