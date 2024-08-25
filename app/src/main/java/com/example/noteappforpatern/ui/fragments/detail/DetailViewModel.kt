package com.example.noteappforpatern.ui.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappforpatern.data.local.entity.NoteEntity
import com.example.noteappforpatern.data.repository.NoteRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: NoteRepository): ViewModel() {

    suspend fun insert(noteEntity: NoteEntity) = viewModelScope.launch { repository.insertNote(noteEntity) }

    suspend fun update(noteEntity: NoteEntity) = viewModelScope.launch { repository.updateNote(noteEntity) }

    fun getByID(id: Long){
        viewModelScope.launch { repository.getNoteById(id) }
    }
}