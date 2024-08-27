package com.example.noteappforpatern.di

import org.koin.dsl.module
import androidx.room.Room
import com.example.noteappforpatern.data.local.db.NoteDataBase
import com.example.noteappforpatern.data.repository.NoteRepository

// Создайте модуль Koin для Room
val roomModule = module {
    single {
        Room.databaseBuilder(get(), NoteDataBase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<NoteDataBase>().noteDao() }

    single { NoteRepository(get()) }
}
