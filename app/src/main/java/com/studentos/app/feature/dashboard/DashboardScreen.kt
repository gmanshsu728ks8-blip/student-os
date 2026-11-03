package com.studentos.app.feature.dashboard

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studentos.app.R
import com.studentos.app.ui.components.BrandHeader
import com.studentos.app.ui.components.PlaceholderCard
import com.studentos.app.ui.components.SectionCard
import com.studentos.app.ui.components.SectionTitle
import com.studentos.app.ui.components.StatTile
import com.studentos.app.ui.theme.Spacing
import com.studentos.app.ui.theme.StudentOsTheme
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

/** Stateful entry point: connects the ViewModel to the stateless [DashboardContent]. */
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LifecycleEventEffect(Lifecycle.Event.ON_START) { viewModel.onScreenStarted() }
    DashboardContent(state = state, modifier = modifier)
}

private data class PlannedFeature(
    @StringRes val title: Int,
    @StringRes val description: Int,
)

private val plannedFeatures = listOf(
    PlannedFeature(R.string.feature_subjects_title, R.string.feature_subjects_desc),
    PlannedFeature(R.string.feature_planner_title, R.string.feature_planner_desc),
    PlannedFeature(R.string.feature_timer_title, R.string.feature_timer_desc),
    PlannedFeature(R.string.feature_tasks_title, R.string.feature_tasks_desc),
    PlannedFeature(R.string.feature_mistakes_title, R.string.feature_mistakes_desc),
    PlannedFeature(R.string.feature_exams_title, R.string.feature_exams_desc),
)

@Composable
fun DashboardContent(
    state: DashboardUiState,
    modifier: Modifier = Modifier,
) {
    val locale = LocalConfiguration.current.locales[0] ?: Locale.getDefault()
    val numberFormat = remember(locale) { NumberFormat.getIntegerInstance(locale) }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            BoxWithConstraints(modifier = Modifier.widthIn(max = Spacing.maxContentWidth).fillMaxSize()) {
                // Phones: 2 columns (stats side by side, features full width).
                // Tablets: 4 columns (all stats in one row, features two per row).
                val columns = if (maxWidth >= Spacing.wideBreakpoint) 4 else 2

                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    contentPadding = PaddingValues(Spacing.md),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.md),
                    verticalArrangement = Arrangement.spacedBy(Spacing.md),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        BrandHeader(
                            name = stringResource(R.string.app_name),
                            tagline = stringResource(R.string.brand_tagline),
                        )
                    }
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        WelcomeCard(today = state.today, locale = locale)
                    }
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SectionTitle(
                            title = stringResource(R.string.stats_title),
                            subtitle = stringResource(R.string.stats_subtitle),
                        )
                    }
                    item {
                        StatTile(
                            label = stringResource(R.string.stat_study_time),
                            value = numberFormat.format(state.stats.studyMinutesToday),
                            unit = stringResource(R.string.stat_study_time_unit),
                        )
                    }
                    item {
                        StatTile(
                            label = stringResource(R.string.stat_tasks),
                            value = numberFormat.format(state.stats.tasksCompleted),
                            unit = stringResource(R.string.stat_tasks_unit),
                        )
                    }
                    item {
                        StatTile(
                            label = stringResource(R.string.stat_mistakes),
                            value = numberFormat.format(state.stats.mistakesToReview),
                            unit = stringResource(R.string.stat_mistakes_unit),
                        )
                    }
                    item {
                        StatTile(
                            label = stringResource(R.string.stat_streak),
                            value = numberFormat.format(state.stats.studyStreakDays),
                            unit = stringResource(R.string.stat_streak_unit),
                        )
                    }
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SectionTitle(
                            title = stringResource(R.string.planned_title),
                            subtitle = stringResource(R.string.planned_subtitle),
                        )
                    }
                    // Span 2 = full width on phones (2 columns), half width on tablets (4 columns).
                    items(plannedFeatures, span = { GridItemSpan(2) }) { feature ->
                        PlaceholderCard(
                            title = stringResource(feature.title),
                            description = stringResource(feature.description),
                            badgeText = stringResource(R.string.badge_not_implemented),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WelcomeCard(today: LocalDate, locale: Locale) {
    val formatter = remember(locale) {
        DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale)
    }
    SectionCard {
        Text(
            text = stringResource(R.string.welcome_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = stringResource(R.string.welcome_body),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = stringResource(R.string.today_label),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = today.format(formatter),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

private val previewState = DashboardUiState(today = LocalDate.of(2026, 11, 3), stats = DashboardStats.Empty)

@Preview(name = "Phone Arabic", locale = "ar", widthDp = 380, heightDp = 800, showBackground = true)
@Composable
private fun DashboardPhonePreview() {
    StudentOsTheme { DashboardContent(state = previewState) }
}

@Preview(name = "Tablet English", locale = "en", widthDp = 900, heightDp = 700, showBackground = true)
@Composable
private fun DashboardTabletPreview() {
    StudentOsTheme { DashboardContent(state = previewState) }
}
