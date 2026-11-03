package com.studentos.app.feature.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Clock
import java.time.LocalDate

/**
 * Holds dashboard state. The [Clock] is injectable so the date logic is unit-testable.
 */
class DashboardViewModel(
    private val clock: Clock = Clock.systemDefaultZone(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        DashboardUiState(today = LocalDate.now(clock), stats = DashboardStats.Empty),
    )
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    /** Call when the screen becomes visible so the date is correct after midnight. */
    fun onScreenStarted() {
        _uiState.update { it.copy(today = LocalDate.now(clock)) }
    }
}
