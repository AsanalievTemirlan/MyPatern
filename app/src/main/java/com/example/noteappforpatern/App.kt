package com.example.noteappforpatern

import android.app.Application
import com.example.noteappforpatern.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(roomModule)
        }
    }
}