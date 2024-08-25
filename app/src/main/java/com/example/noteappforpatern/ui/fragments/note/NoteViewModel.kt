package com.example.noteappforpatern.ui.fragments.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappforpatern.data.local.entity.NoteEntity
import com.example.noteappforpatern.data.repository.NoteRepository

class NoteViewModel(private val repository: NoteRepository): ViewModel() {

    fun getNote() = repository.getNote()

    suspend fun delete(noteModel: NoteEntity) = repository.deleteNote(noteModel)
}