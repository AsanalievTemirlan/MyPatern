package com.example.noteappforpatern.data.repository

import androidx.lifecycle.LiveData
import com.example.noteappforpatern.data.local.dao.NoteDao
import com.example.noteappforpatern.data.local.entity.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    fun getNote(): LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    fun getNoteById(id: Long): LiveData<NoteEntity> = noteDao.getNoteById(id)

    suspend fun insertNote(note: NoteEntity) = noteDao.insertNote(note)

    suspend fun updateNote(note: NoteEntity) = noteDao.updateNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

}