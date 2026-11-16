package com.linguamaster.app.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.linguamaster.app.data.model.AppLanguage
import java.util.Locale

class TtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            setLanguage(AppLanguage.ENGLISH)
        } else {
            Log.e("TtsManager", "TextToSpeech initialization failed with status $status")
        }
    }

    fun setLanguage(language: AppLanguage) {
        if (!isInitialized) return
        val locale = when (language) {
            AppLanguage.ENGLISH -> Locale.US
            AppLanguage.GERMAN -> Locale.GERMANY
        }
        val result = tts?.setLanguage(locale)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.w("TtsManager", "Locale $locale missing data or not supported")
        }
    }

    fun speak(text: String, language: AppLanguage? = null) {
        if (!isInitialized || text.isBlank()) return
        if (language != null) {
            setLanguage(language)
        }
        tts?.setSpeechRate(0.85f) // Slightly slower for clear language learning pronunciation
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "UTTERANCE_${System.currentTimeMillis()}")
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isInitialized = false
    }
}
