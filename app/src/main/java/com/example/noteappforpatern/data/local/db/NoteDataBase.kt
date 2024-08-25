package com.example.noteappforpatern.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappforpatern.data.local.dao.NoteDao
import com.example.noteappforpatern.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}