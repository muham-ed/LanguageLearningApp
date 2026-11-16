package com.linguamaster.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.linguamaster.app.data.model.Lesson
import com.linguamaster.app.ui.components.AppTab
import com.linguamaster.app.ui.components.BottomNavBar
import com.linguamaster.app.ui.components.LanguagePickerSheet
import com.linguamaster.app.ui.components.TopBarHeader
import com.linguamaster.app.ui.screens.home.HomeScreen
import com.linguamaster.app.ui.screens.lesson.LessonScreen
import com.linguamaster.app.ui.screens.practice.PracticeScreen
import com.linguamaster.app.ui.screens.profile.ProfileScreen
import com.linguamaster.app.ui.screens.vocabulary.VocabularyScreen
import com.linguamaster.app.ui.theme.LinguaMasterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinguaMasterTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val repository = LinguaApp.instance.repository
    val userStats by repository.userStats.collectAsState()
    val currentLanguage by repository.currentLanguage.collectAsState()

    var selectedTab by remember { mutableStateOf(AppTab.LEARN) }
    var activeLesson by remember { mutableStateOf<Lesson?>(null) }
    var showLanguageSheet by remember { mutableStateOf(false) }

    // If an active lesson is selected, show full screen lesson learning experience
    if (activeLesson != null) {
        LessonScreen(
            lesson = activeLesson!!,
            activeLanguage = currentLanguage,
            onClose = { activeLesson = null },
            onCompleted = {
                activeLesson = null
            }
        )
        return
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            // Persistent top status header
            TopBarHeader(
                currentLanguage = currentLanguage,
                userStats = userStats,
                onLanguageClick = { showLanguageSheet = true }
            )

            // Screen switcher based on selected bottom tab
            Box(modifier = Modifier.weight(1f)) {
                when (selectedTab) {
                    AppTab.LEARN -> {
                        HomeScreen(
                            units = repository.getUnits(),
                            userStats = userStats,
                            onStartLesson = { lessonId ->
                                activeLesson = repository.getLessonById(lessonId)
                            }
                        )
                    }
                    AppTab.PRACTICE -> {
                        PracticeScreen(
                            onStartPracticeQuiz = {
                                // Pick first lesson or an available lesson for practice workout
                                val firstLesson = repository.getUnits().firstOrNull()?.lessons?.firstOrNull()
                                if (firstLesson != null) {
                                    activeLesson = firstLesson
                                }
                            }
                        )
                    }
                    AppTab.VOCABULARY -> {
                        VocabularyScreen(
                            vocabularies = repository.getAllVocabularies(),
                            activeLanguage = currentLanguage,
                            onToggleBookmark = { wordId ->
                                repository.toggleBookmark(wordId)
                            }
                        )
                    }
                    AppTab.PROFILE -> {
                        ProfileScreen(
                            userStats = userStats,
                            activeLanguage = currentLanguage,
                            onRefillHearts = { repository.refillHearts() },
                            onChangeLanguage = { showLanguageSheet = true }
                        )
                    }
                }
            }
        }
    }

    if (showLanguageSheet) {
        LanguagePickerSheet(
            currentLanguage = currentLanguage,
            onSelectLanguage = { selected ->
                repository.switchLanguage(selected)
            },
            onDismiss = { showLanguageSheet = false }
        )
    }
}
