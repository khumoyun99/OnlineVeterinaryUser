package com.example.onlineveterinaryuser.presentation.activity

import android.app.Application
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider


class App:Application() {

    override fun onCreate() {
        super.onCreate()

        EmojiManager.install(GoogleEmojiProvider())

    }
}