package com.linguamaster.app.ui.screens.lesson

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linguamaster.app.LinguaApp
import com.linguamaster.app.data.model.AppLanguage
import com.linguamaster.app.data.model.ExerciseItem
import com.linguamaster.app.data.model.ExerciseType
import com.linguamaster.app.data.model.Lesson
import com.linguamaster.app.data.model.VocabularyItem
import com.linguamaster.app.ui.theme.CardBorderLight
import com.linguamaster.app.ui.theme.ElectricBlue
import com.linguamaster.app.ui.theme.EmeraldDark
import com.linguamaster.app.ui.theme.EmeraldGreen
import com.linguamaster.app.ui.theme.ErrorRed
import com.linguamaster.app.ui.theme.RoseRed
import com.linguamaster.app.ui.theme.SuccessGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LessonScreen(
    lesson: Lesson,
    activeLanguage: AppLanguage,
    onClose: () -> Unit,
    onCompleted: (earnedXp: Int) -> Unit
) {
    val ttsManager = LinguaApp.instance.ttsManager
    val repository = LinguaApp.instance.repository

    // Combine vocabularies and exercises into steps
    val totalSteps = lesson.vocabularies.size + lesson.exercises.size
    var currentStepIndex by remember { mutableStateOf(0) }
    var hearts by remember { mutableStateOf(5) }
    var isLessonFinished by remember { mutableStateOf(false) }

    // Answer evaluation state
    var selectedOption by remember { mutableStateOf<String?>(null) }
    val sentenceSelectedTokens = remember { mutableStateListOf<String>() }
    var isChecked by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    val currentVocab = if (currentStepIndex < lesson.vocabularies.size) {
        lesson.vocabularies[currentStepIndex]
    } else null

    val currentExercise = if (currentVocab == null && currentStepIndex - lesson.vocabularies.size < lesson.exercises.size) {
        lesson.exercises[currentStepIndex - lesson.vocabularies.size]
    } else null

    // Speak audio automatically when entering vocab or listening exercise
    LaunchedEffect(currentStepIndex) {
        selectedOption = null
        sentenceSelectedTokens.clear()
        isChecked = false
        isCorrect = false

        if (currentVocab != null) {
            ttsManager.speak(currentVocab.wordOriginal, activeLanguage)
        } else if (currentExercise?.type == ExerciseType.LISTENING) {
            ttsManager.speak(currentExercise.targetPhrase, activeLanguage)
        }
    }

    if (isLessonFinished) {
        LessonSuccessView(
            xpEarned = lesson.xpReward,
            onFinish = {
                repository.completeLesson(lesson.id, lesson.xpReward)
                onCompleted(lesson.xpReward)
            }
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top Bar: Exit button, Progress Bar, Hearts
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            val progress = (currentStepIndex.toFloat() / totalSteps.coerceAtLeast(1))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = EmeraldGreen,
                trackColor = CardBorderLight
            )

            Spacer(modifier = Modifier.width(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = RoseRed,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$hearts",
                    style = MaterialTheme.typography.labelLarge,
                    color = RoseRed,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Center Content Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            if (currentVocab != null) {
                // Flashcard presentation
                VocabFlashcardView(
                    vocab = currentVocab,
                    onPlayAudio = { ttsManager.speak(currentVocab.wordOriginal, activeLanguage) }
                )
            } else if (currentExercise != null) {
                when (currentExercise.type) {
                    ExerciseType.MULTIPLE_CHOICE -> {
                        MultipleChoiceExerciseView(
                            exercise = currentExercise,
                            selectedOption = selectedOption,
                            onOptionSelected = { if (!isChecked) selectedOption = it }
                        )
                    }
                    ExerciseType.LISTENING -> {
                        ListeningExerciseView(
                            exercise = currentExercise,
                            selectedOption = selectedOption,
                            onOptionSelected = { if (!isChecked) selectedOption = it },
                            onPlayAudio = { ttsManager.speak(currentExercise.targetPhrase, activeLanguage) }
                        )
                    }
                    ExerciseType.SENTENCE_BUILDER -> {
                        SentenceBuilderExerciseView(
                            exercise = currentExercise,
                            selectedTokens = sentenceSelectedTokens,
                            onTokenClick = { token ->
                                if (!isChecked) {
                                    if (sentenceSelectedTokens.contains(token)) {
                                        sentenceSelectedTokens.remove(token)
                                    } else {
                                        sentenceSelectedTokens.add(token)
                                    }
                                }
                            }
                        )
                    }
                    ExerciseType.MATCHING_PAIRS -> {
                        MatchingPairsExerciseView(
                            exercise = currentExercise,
                            onCompletedAll = {
                                selectedOption = "COMPLETED"
                            }
                        )
                    }
                    else -> Unit
                }
            }
        }

        // Bottom Action & Feedback Area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (!isChecked) MaterialTheme.colorScheme.surface
                    else if (isCorrect) SuccessGreen.copy(alpha = 0.12f)
                    else ErrorRed.copy(alpha = 0.12f)
                )
                .padding(20.dp)
        ) {
            AnimatedVisibility(
                visible = isChecked,
                enter = fadeIn() + slideInVertically { it / 2 }
            ) {
                Column(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(
                        text = if (isCorrect) "إجابة ممتازة! أحسنت 🌟" else "إجابة خاطئة! ❌",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isCorrect) SuccessGreen else ErrorRed
                    )
                    if (!isCorrect && currentExercise != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "الإجابة الصحيحة هي: ${currentExercise.correctAnswer}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Check / Continue Button
            Button(
                onClick = {
                    if (currentVocab != null) {
                        // Flashcard step: simple advance
                        if (currentStepIndex + 1 < totalSteps) {
                            currentStepIndex++
                        } else {
                            isLessonFinished = true
                        }
                    } else if (currentExercise != null) {
                        if (!isChecked) {
                            // Validate answer
                            val userAnswer = when (currentExercise.type) {
                                ExerciseType.SENTENCE_BUILDER -> sentenceSelectedTokens.joinToString(" ")
                                ExerciseType.MATCHING_PAIRS -> selectedOption ?: ""
                                else -> selectedOption ?: ""
                            }
                            isCorrect = if (currentExercise.type == ExerciseType.MATCHING_PAIRS) {
                                selectedOption == "COMPLETED"
                            } else {
                                userAnswer.trim().equals(currentExercise.correctAnswer.trim(), ignoreCase = true)
                            }

                            if (!isCorrect) {
                                hearts = (hearts - 1).coerceAtLeast(0)
                                repository.deductHeart()
                            }
                            isChecked = true
                        } else {
                            // Proceed to next step
                            if (currentStepIndex + 1 < totalSteps) {
                                currentStepIndex++
                            } else {
                                isLessonFinished = true
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isChecked) EmeraldGreen
                    else if (isCorrect) SuccessGreen
                    else ErrorRed
                ),
                enabled = if (currentVocab != null) true
                else if (isChecked) true
                else if (currentExercise?.type == ExerciseType.SENTENCE_BUILDER) sentenceSelectedTokens.isNotEmpty()
                else selectedOption != null
            ) {
                Text(
                    text = if (currentVocab != null) "فهمت الكلمة، التالي"
                    else if (!isChecked) "تحقق من الإجابة"
                    else "متابعة",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// ---------------------------------------------------------------------
// Sub-views for Flashcards & Exercises
// ---------------------------------------------------------------------

@Composable
fun VocabFlashcardView(
    vocab: VocabularyItem,
    onPlayAudio: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CardBorderLight, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "مفردة جديدة 💡",
                style = MaterialTheme.typography.labelLarge,
                color = EmeraldDark
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Audio Speaker Icon Button
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(EmeraldGreen.copy(alpha = 0.15f))
                    .clickable { onPlayAudio() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Play Pronunciation",
                    tint = EmeraldGreen,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = vocab.wordOriginal,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            if (vocab.phonetic.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = vocab.phonetic,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = vocab.wordArabic,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldDark
                )
            }

            if (vocab.exampleOriginal.isNotBlank()) {
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "مثال: ${vocab.exampleOriginal}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = vocab.exampleArabic,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
fun MultipleChoiceExerciseView(
    exercise: ExerciseItem,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = exercise.prompt,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "\"${exercise.targetPhrase}\"",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = ElectricBlue
        )

        Spacer(modifier = Modifier.height(24.dp))

        exercise.options.forEach { option ->
            val isSelected = option == selectedOption
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = if (isSelected) EmeraldGreen else CardBorderLight,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { onOptionSelected(option) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) EmeraldGreen.copy(alpha = 0.1f)
                    else MaterialTheme.colorScheme.surface
                )
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ListeningExerciseView(
    exercise: ExerciseItem,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    onPlayAudio: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = exercise.prompt,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(ElectricBlue.copy(alpha = 0.15f))
                .clickable { onPlayAudio() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.VolumeUp,
                contentDescription = "Listen",
                tint = ElectricBlue,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "اضغط للاستماع مرة أخرى",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        exercise.options.forEach { option ->
            val isSelected = option == selectedOption
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = if (isSelected) EmeraldGreen else CardBorderLight,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { onOptionSelected(option) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) EmeraldGreen.copy(alpha = 0.1f)
                    else MaterialTheme.colorScheme.surface
                )
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SentenceBuilderExerciseView(
    exercise: ExerciseItem,
    selectedTokens: List<String>,
    onTokenClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = exercise.prompt,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Selected tokens area (The sentence being built)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, CardBorderLight, RoundedCornerShape(16.dp))
                .padding(12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (selectedTokens.isEmpty()) {
                Text(
                    text = "اضغط على الكلمات أدناه بالترتيب...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    selectedTokens.forEach { token ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(EmeraldGreen.copy(alpha = 0.15f))
                                .border(1.dp, EmeraldGreen, RoundedCornerShape(10.dp))
                                .clickable { onTokenClick(token) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = token,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldDark
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Available scrambled tokens to pick from
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            exercise.options.forEach { token ->
                val isPicked = selectedTokens.contains(token)
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isPicked) MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                            else MaterialTheme.colorScheme.surface
                        )
                        .border(1.dp, CardBorderLight, RoundedCornerShape(12.dp))
                        .clickable(enabled = !isPicked) { onTokenClick(token) }
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = token,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isPicked) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun MatchingPairsExerciseView(
    exercise: ExerciseItem,
    onCompletedAll: () -> Unit
) {
    var selectedLeft by remember { mutableStateOf<String?>(null) }
    var selectedRight by remember { mutableStateOf<String?>(null) }
    val matchedKeys = remember { mutableStateListOf<String>() }

    val leftItems = remember { exercise.matchingPairs.keys.toList() }
    val rightItems = remember { exercise.matchingPairs.values.shuffled() }

    LaunchedEffect(matchedKeys.size) {
        if (matchedKeys.size == exercise.matchingPairs.size && exercise.matchingPairs.isNotEmpty()) {
            onCompletedAll()
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = exercise.prompt,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left column (Foreign words)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                leftItems.forEach { left ->
                    val isMatched = matchedKeys.contains(left)
                    val isSelected = selectedLeft == left
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when {
                                    isMatched -> SuccessGreen.copy(alpha = 0.15f)
                                    isSelected -> ElectricBlue.copy(alpha = 0.15f)
                                    else -> MaterialTheme.colorScheme.surface
                                }
                            )
                            .border(
                                width = 1.dp,
                                color = when {
                                    isMatched -> SuccessGreen
                                    isSelected -> ElectricBlue
                                    else -> CardBorderLight
                                },
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable(enabled = !isMatched) {
                                selectedLeft = left
                                if (selectedRight != null) {
                                    // Check match
                                    if (exercise.matchingPairs[left] == selectedRight) {
                                        matchedKeys.add(left)
                                        selectedLeft = null
                                        selectedRight = null
                                    } else {
                                        selectedLeft = null
                                        selectedRight = null
                                    }
                                }
                            }
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = left,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right column (Arabic words)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rightItems.forEach { right ->
                    val isMatched = matchedKeys.any { exercise.matchingPairs[it] == right }
                    val isSelected = selectedRight == right
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when {
                                    isMatched -> SuccessGreen.copy(alpha = 0.15f)
                                    isSelected -> ElectricBlue.copy(alpha = 0.15f)
                                    else -> MaterialTheme.colorScheme.surface
                                }
                            )
                            .border(
                                width = 1.dp,
                                color = when {
                                    isMatched -> SuccessGreen
                                    isSelected -> ElectricBlue
                                    else -> CardBorderLight
                                },
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable(enabled = !isMatched) {
                                selectedRight = right
                                if (selectedLeft != null) {
                                    if (exercise.matchingPairs[selectedLeft] == right) {
                                        matchedKeys.add(selectedLeft!!)
                                        selectedLeft = null
                                        selectedRight = null
                                    } else {
                                        selectedLeft = null
                                        selectedRight = null
                                    }
                                }
                            }
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = right,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LessonSuccessView(
    xpEarned: Int,
    onFinish: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "🎉", fontSize = 72.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "تهانينا! أكملت الدرس بنجاح",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "أنت تتقدم بثبات نحو إتقان اللغة!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = EmeraldGreen.copy(alpha = 0.12f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "⭐", fontSize = 28.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "+$xpEarned XP",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldDark
                    )
                    Text(
                        text = "نقاط خبرة مكتسبة",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onFinish,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
        ) {
            Text(
                text = "متابعة التعلم",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
