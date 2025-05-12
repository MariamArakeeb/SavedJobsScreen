package com.example.jobsearchapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF117E7E),       // Primary Blue

    tertiary = Color(0xFF094F4F),

    onPrimary = Color.White,           // White Text on Primary
    secondary = Color(0xFFEC7121),     // Accent Green
    background = Color(0xFFF5F5F5),    // Light Gray Background
    surface = Color.White,             // White Cards
    onSurface = Color(0xFF1C1B1F),     // Dark Text for Readability
    onSecondary = Color.White,         // White Text on Secondary
    error = Color(0xFFDC3545),         // Crimson Red for Warnings
    onError = Color.White,             // White Text on Error
    outline = Color(0xFFCCCCCC),       // Soft Gray Borders
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0E5757),       // Primary Blue

    tertiary = Color(0xFF093F3F),

    onPrimary = Color.White,           // White Text on Primary
    secondary = Color(0xFFAF511D),     // Accent Green
    background = Color(0xFF121212),    // Deep Charcoal Black
    surface = Color(0xFF1E1E1E),       // Graphite Gray Surface
    onSurface = Color(0xFFEAEAEA),     // Soft White Text
    onSecondary = Color.White,         // White Text on Secondary
    error = Color(0xFFFF4C4C),         // Fiery Red for Warnings
    onError = Color.White,             // White Text on Error
    outline = Color(0xFF444444),       // Gunmetal Gray Borders
)

@Composable
fun JobSearchApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}