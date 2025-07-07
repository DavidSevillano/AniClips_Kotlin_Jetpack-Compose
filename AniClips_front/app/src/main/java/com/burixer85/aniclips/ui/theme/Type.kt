package com.burixer85.aniclips.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.burixer85.aniclips.R

val lexend = FontFamily(
    Font(R.font.lexend_regular, FontWeight.Normal)
)

val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)