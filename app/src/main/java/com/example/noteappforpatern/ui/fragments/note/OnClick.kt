package com.example.noteappforpatern.ui.fragments.note

import com.example.noteappforpatern.data.local.entity.NoteEntity

interface OnClick {
    fun onItemClick(noteModel: NoteEntity)
}
