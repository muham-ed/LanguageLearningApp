package com.linguamaster.app.data.model

import kotlinx.serialization.Serializable

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String, val flag: String) {
    ENGLISH("en", "الإنجليزية", "English", "🇬🇧"),
    GERMAN("de", "الألمانية", "Deutsch", "🇩🇪");

    companion object {
        fun fromCode(code: String): AppLanguage =
            entries.find { it.code.equals(code, ignoreCase = true) } ?: ENGLISH
    }
}

enum class ExerciseType {
    FLASHCARD,
    MULTIPLE_CHOICE,
    SENTENCE_BUILDER,
    LISTENING,
    MATCHING_PAIRS
}

@Serializable
data class CourseUnit(
    val id: String,
    val languageCode: String,
    val level: String, // "A1", "A2", "B1"
    val order: Int,
    val titleAr: String,
    val titleOriginal: String,
    val description: String,
    val iconEmoji: String,
    val lessons: List<Lesson> = emptyList()
)

@Serializable
data class Lesson(
    val id: String,
    val unitId: String,
    val order: Int,
    val titleAr: String,
    val titleOriginal: String,
    val xpReward: Int = 20,
    val vocabularies: List<VocabularyItem> = emptyList(),
    val exercises: List<ExerciseItem> = emptyList()
)

@Serializable
data class VocabularyItem(
    val id: String,
    val lessonId: String,
    val languageCode: String,
    val wordOriginal: String,
    val wordArabic: String,
    val phonetic: String = "",
    val exampleOriginal: String = "",
    val exampleArabic: String = "",
    val category: String = "General",
    val isBookmarked: Boolean = false
)

@Serializable
data class ExerciseItem(
    val id: String,
    val lessonId: String,
    val type: ExerciseType,
    val prompt: String, // Question in Arabic or direction
    val targetPhrase: String, // Word or sentence in original language (en/de)
    val correctAnswer: String,
    val options: List<String> = emptyList(), // For multiple choice or sentence builder scrambled tokens
    val matchingPairs: Map<String, String> = emptyMap() // For word matching (original -> arabic)
)

data class UserStats(
    val totalXp: Int = 120,
    val streakDays: Int = 3,
    val hearts: Int = 5,
    val maxHearts: Int = 5,
    val activeLanguage: AppLanguage = AppLanguage.ENGLISH,
    val completedLessonIds: Set<String> = emptySet(),
    val bookmarkedWordIds: Set<String> = emptySet()
)
