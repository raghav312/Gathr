package com.example.gathr.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Table creation
@Entity(tableName = "reminders_table")

//columns details as data members of the class Reminder
class Reminder (
    @ColumnInfo(name = "title") var reminderTitle:String,
    @ColumnInfo(name = "date") var reminderDate: String,
    @ColumnInfo(name = "time") var reminderTime: String,
    @ColumnInfo(name = "active") var active: Boolean,
    )
    {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id = 0
}
