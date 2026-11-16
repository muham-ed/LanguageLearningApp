package com.linguamaster.app

import android.app.Application
import com.linguamaster.app.audio.TtsManager
import com.linguamaster.app.data.repository.CurriculumRepository

class LinguaApp : Application() {

    lateinit var ttsManager: TtsManager
        private set

    lateinit var repository: CurriculumRepository
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        ttsManager = TtsManager(this)
        repository = CurriculumRepository(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ttsManager.shutdown()
    }

    companion object {
        lateinit var instance: LinguaApp
            private set
    }
}
