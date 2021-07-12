package com.example.gathr.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Table creation
@Entity(tableName = "notes_table")

//column details

//Table:Class
//column:data members

class Note(
    @ColumnInfo(name = "notes_body")val text:String,
    @ColumnInfo(name = "notes_title")val title:String
    ) {
    @PrimaryKey(autoGenerate = true) var id = 0
}