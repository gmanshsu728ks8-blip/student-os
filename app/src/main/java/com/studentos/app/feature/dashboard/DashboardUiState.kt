package com.studentos.app.feature.dashboard

import java.time.LocalDate

/**
 * Study statistics shown on the dashboard.
 *
 * There is no data layer yet, so [Empty] is the only possible value, and it is a genuine
 * "nothing recorded" state, not sample data. Phase 2+ will populate this from Room.
 */
data class DashboardStats(
    val studyMinutesToday: Int,
    val tasksCompleted: Int,
    val mistakesToReview: Int,
    val studyStreakDays: Int,
) {
    companion object {
        val Empty = DashboardStats(
            studyMinutesToday = 0,
            tasksCompleted = 0,
            mistakesToReview = 0,
            studyStreakDays = 0,
        )
    }
}

data class DashboardUiState(
    val today: LocalDate,
    val stats: DashboardStats,
)
