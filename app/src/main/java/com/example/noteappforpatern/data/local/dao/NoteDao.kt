package com.example.noteappforpatern.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteappforpatern.data.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteModel")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM noteModel WHERE id = :id")
    fun getNoteById(id: Long): LiveData<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteModel: NoteEntity)

    @Delete
    suspend fun deleteNote(noteModel: NoteEntity)

    @Update
    suspend fun updateNote(noteModel: NoteEntity)
}
