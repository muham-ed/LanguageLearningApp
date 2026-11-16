package com.linguamaster.app.ui.screens.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linguamaster.app.ui.theme.AmberGold
import com.linguamaster.app.ui.theme.CardBorderLight
import com.linguamaster.app.ui.theme.ElectricBlue
import com.linguamaster.app.ui.theme.EmeraldGreen
import com.linguamaster.app.ui.theme.VioletPurple

@Composable
fun PracticeScreen(
    onStartPracticeQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "صالة التمارين والتحديات ⚡",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ثبّت معلوماتك واكسب المزيد من نقاط الخبرة XP عبر التحديات اليومية",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }

        item {
            PracticeOptionCard(
                title = "تحدي السرعة اليومي",
                subtitle = "أجب عن 5 أسئلة سريعة لتعزيز الذاكرة",
                badgeText = "+30 XP",
                badgeColor = AmberGold,
                icon = Icons.Default.Bolt,
                iconBg = AmberGold.copy(alpha = 0.15f),
                iconTint = AmberGold,
                onClick = onStartPracticeQuiz
            )
        }

        item {
            PracticeOptionCard(
                title = "تمرين الاستماع والفهم",
                subtitle = "استمع للكلمات والنطق الصحيح بدون قراءة النص",
                badgeText = "+25 XP",
                badgeColor = ElectricBlue,
                icon = Icons.Default.Hearing,
                iconBg = ElectricBlue.copy(alpha = 0.15f),
                iconTint = ElectricBlue,
                onClick = onStartPracticeQuiz
            )
        }

        item {
            PracticeOptionCard(
                title = "مراجعة الكلمات المحفوظة",
                subtitle = "راجع الكلمات التي قمت بتمييزها بنجمة وتأكد من حفظها",
                badgeText = "+20 XP",
                badgeColor = EmeraldGreen,
                icon = Icons.Default.Sync,
                iconBg = EmeraldGreen.copy(alpha = 0.15f),
                iconTint = EmeraldGreen,
                onClick = onStartPracticeQuiz
            )
        }

        item {
            PracticeOptionCard(
                title = "ماراثون القواعد والتركيب",
                subtitle = "تدرب على بناء الجمل المعقدة وترتيب الكلمات",
                badgeText = "+35 XP",
                badgeColor = VioletPurple,
                icon = Icons.Default.FitnessCenter,
                iconBg = VioletPurple.copy(alpha = 0.15f),
                iconTint = VioletPurple,
                onClick = onStartPracticeQuiz
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun PracticeOptionCard(
    title: String,
    subtitle: String,
    badgeText: String,
    badgeColor: Color,
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CardBorderLight, RoundedCornerShape(20.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(badgeColor.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badgeText,
                                style = MaterialTheme.typography.labelSmall,
                                color = badgeColor,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
