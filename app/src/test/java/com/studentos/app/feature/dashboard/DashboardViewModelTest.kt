package com.studentos.app.feature.dashboard

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class DashboardViewModelTest {

    private val cairo = ZoneId.of("Africa/Cairo")

    /** A clock whose time can be moved, to simulate the app staying open past midnight. */
    private class MovableClock(var now: Instant, private val zone: ZoneId) : Clock() {
        override fun getZone(): ZoneId = zone
        override fun withZone(zone: ZoneId): Clock = MovableClock(now, zone)
        override fun instant(): Instant = now
    }

    @Test
    fun initialState_usesClockDate() {
        val clock = Clock.fixed(Instant.parse("2026-11-03T10:00:00Z"), cairo)
        val viewModel = DashboardViewModel(clock)
        assertEquals(LocalDate.of(2026, 11, 3), viewModel.uiState.value.today)
    }

    @Test
    fun initialState_hasAllZeroStats() {
        val clock = Clock.fixed(Instant.parse("2026-11-03T10:00:00Z"), cairo)
        val stats = DashboardViewModel(clock).uiState.value.stats
        assertEquals(DashboardStats(0, 0, 0, 0), stats)
    }

    @Test
    fun onScreenStarted_refreshesDateAfterMidnight() {
        val clock = MovableClock(Instant.parse("2026-11-03T10:00:00Z"), cairo)
        val viewModel = DashboardViewModel(clock)

        clock.now = Instant.parse("2026-11-04T10:00:00Z")
        viewModel.onScreenStarted()

        assertEquals(LocalDate.of(2026, 11, 4), viewModel.uiState.value.today)
    }
}
