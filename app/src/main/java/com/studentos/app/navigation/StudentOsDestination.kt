package com.studentos.app.navigation

/** All navigable destinations. Phase 1 has only the dashboard; later phases add entries here. */
sealed class StudentOsDestination(val route: String) {
    data object Dashboard : StudentOsDestination("dashboard")
}
