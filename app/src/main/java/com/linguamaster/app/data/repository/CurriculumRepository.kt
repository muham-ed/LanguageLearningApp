package com.linguamaster.app.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.linguamaster.app.data.CurriculumDataSource
import com.linguamaster.app.data.model.AppLanguage
import com.linguamaster.app.data.model.CourseUnit
import com.linguamaster.app.data.model.Lesson
import com.linguamaster.app.data.model.UserStats
import com.linguamaster.app.data.model.VocabularyItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CurriculumRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("lingua_prefs", Context.MODE_PRIVATE)

    private val _userStats = MutableStateFlow(loadStats())
    val userStats: StateFlow<UserStats> = _userStats.asStateFlow()

    private val _currentLanguage = MutableStateFlow(
        AppLanguage.fromCode(prefs.getString("selected_lang", AppLanguage.ENGLISH.code) ?: "en")
    )
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private fun loadStats(): UserStats {
        val xp = prefs.getInt("total_xp", 120)
        val streak = prefs.getInt("streak_days", 3)
        val hearts = prefs.getInt("hearts", 5)
        val lang = AppLanguage.fromCode(prefs.getString("selected_lang", "en") ?: "en")
        val completed = prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()
        val bookmarked = prefs.getStringSet("bookmarked_words", emptySet()) ?: emptySet()
        return UserStats(
            totalXp = xp,
            streakDays = streak,
            hearts = hearts,
            activeLanguage = lang,
            completedLessonIds = completed,
            bookmarkedWordIds = bookmarked
        )
    }

    private fun saveStats(stats: UserStats) {
        prefs.edit()
            .putInt("total_xp", stats.totalXp)
            .putInt("streak_days", stats.streakDays)
            .putInt("hearts", stats.hearts)
            .putString("selected_lang", stats.activeLanguage.code)
            .putStringSet("completed_lessons", stats.completedLessonIds)
            .putStringSet("bookmarked_words", stats.bookmarkedWordIds)
            .apply()
        _userStats.value = stats
    }

    fun switchLanguage(language: AppLanguage) {
        _currentLanguage.value = language
        saveStats(_userStats.value.copy(activeLanguage = language))
    }

    fun getUnits(): List<CourseUnit> {
        return CurriculumDataSource.getUnitsForLanguage(_currentLanguage.value)
    }

    fun getLessonById(lessonId: String): Lesson? {
        return getUnits().flatMap { it.lessons }.find { it.id == lessonId }
    }

    fun getAllVocabularies(): List<VocabularyItem> {
        val bookmarks = _userStats.value.bookmarkedWordIds
        return CurriculumDataSource.getAllVocabularies(_currentLanguage.value).map {
            it.copy(isBookmarked = bookmarks.contains(it.id))
        }
    }

    fun getBookmarkedVocabularies(): List<VocabularyItem> {
        return getAllVocabularies().filter { it.isBookmarked }
    }

    fun toggleBookmark(wordId: String) {
        val currentSet = _userStats.value.bookmarkedWordIds.toMutableSet()
        if (currentSet.contains(wordId)) {
            currentSet.remove(wordId)
        } else {
            currentSet.add(wordId)
        }
        saveStats(_userStats.value.copy(bookmarkedWordIds = currentSet))
    }

    fun deductHeart(): Boolean {
        val current = _userStats.value
        val newHearts = (current.hearts - 1).coerceAtLeast(0)
        saveStats(current.copy(hearts = newHearts))
        return newHearts > 0
    }

    fun refillHearts() {
        val current = _userStats.value
        saveStats(current.copy(hearts = current.maxHearts))
    }

    fun completeLesson(lessonId: String, earnedXp: Int) {
        val current = _userStats.value
        val completed = current.completedLessonIds.toMutableSet().apply { add(lessonId) }
        saveStats(
            current.copy(
                totalXp = current.totalXp + earnedXp,
                completedLessonIds = completed
            )
        )
    }
}
