package com.studentos.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Arabic-aware typography: generous line heights (Arabic has tall ascenders and diacritics) and
 * zero letter spacing (positive tracking breaks the joined letterforms of Arabic script).
 * Uses the system font, which provides Arabic glyphs on every supported device.
 */
private val Base = Typography()

val StudentOsTypography = Typography(
    headlineMedium = Base.headlineMedium.copy(
        fontWeight = FontWeight.Bold, fontSize = 28.sp, lineHeight = 40.sp, letterSpacing = 0.sp,
    ),
    titleLarge = Base.titleLarge.copy(
        fontWeight = FontWeight.Bold, fontSize = 22.sp, lineHeight = 32.sp, letterSpacing = 0.sp,
    ),
    titleMedium = Base.titleMedium.copy(
        fontWeight = FontWeight.SemiBold, fontSize = 17.sp, lineHeight = 26.sp, letterSpacing = 0.sp,
    ),
    bodyLarge = Base.bodyLarge.copy(fontSize = 16.sp, lineHeight = 26.sp, letterSpacing = 0.sp),
    bodyMedium = Base.bodyMedium.copy(fontSize = 14.sp, lineHeight = 22.sp, letterSpacing = 0.sp),
    labelLarge = Base.labelLarge.copy(
        fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.sp,
    ),
    labelMedium = Base.labelMedium.copy(
        fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 18.sp, letterSpacing = 0.sp,
    ),
)
