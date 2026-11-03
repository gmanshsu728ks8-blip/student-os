package com.studentos.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

object Spacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 32.dp

    /** Content never stretches wider than this, which keeps tablets readable. */
    val maxContentWidth = 840.dp

    /** Width from which layouts switch from phone to tablet arrangement. */
    val wideBreakpoint = 600.dp
}

val StudentOsShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
)
