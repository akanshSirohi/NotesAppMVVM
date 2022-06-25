package com.akansh.notesappmvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}