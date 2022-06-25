package com.akansh.notesappmvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name="noteText") val text: String) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}