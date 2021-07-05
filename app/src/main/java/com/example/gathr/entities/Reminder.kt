package com.example.gathr.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders_table")
class Reminder (
    @ColumnInfo(name = "title") var reminderTitle:String,
    @ColumnInfo(name = "date") var reminderDate: String,
    @ColumnInfo(name = "time") var reminderTime: String,
    @ColumnInfo(name = "active") var active: Boolean,
    )
    {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id = 0
}
