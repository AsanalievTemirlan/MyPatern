package com.example.noteappforpatern.data.local.entity

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val color: Int = Color.BLACK,
    val time: String,
    val date: String
)