package br.com.vapstor.pokedex.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Neutros
private val Slate950 = Color(0xFF0D1117)
private val Slate900 = Color(0xFF141B24)
private val Slate800 = Color(0xFF1E2936)
private val Slate700 = Color(0xFF334155)
private val Slate200 = Color(0xFFD8E0E8)
private val Slate100 = Color(0xFFEBF0F5)
private val Slate50 = Color(0xFFF6F8FA)
private val PureWhite = Color(0xFFFFFFFF)

// Primária: Carmim sóbrio
private val Crimson700 = Color(0xFF8E1D2C)
private val Crimson600 = Color(0xFFA62639)
private val Crimson300 = Color(0xFFE05A6B)
private val Crimson100 = Color(0xFFFCE8EB)
private val Crimson900Muted = Color(0xFF3B151C)

// Secundária: Petróleo / Teal técnico
private val Teal700 = Color(0xFF155658)
private val Teal600 = Color(0xFF1D6F72)
private val Teal300 = Color(0xFF4FB3B6)
private val Teal100 = Color(0xFFE3F3F3)
private val Teal900Muted = Color(0xFF122E30)

// Erro
private val ErrorLight = Color(0xFFB3261E)
private val ErrorDark = Color(0xFFF2B8B5)

private val LightColorScheme = lightColorScheme(
    primary = Crimson600,
    onPrimary = PureWhite,
    primaryContainer = Crimson100,
    onPrimaryContainer = Crimson700,
    secondary = Teal600,
    onSecondary = PureWhite,
    secondaryContainer = Teal100,
    onSecondaryContainer = Teal700,
    background = Slate50,
    onBackground = Slate900,
    surface = Slate50,
    onSurface = Slate900,
    surfaceVariant = Slate100,
    onSurfaceVariant = Slate700,
    outline = Slate200,
    error = ErrorLight,
    onError = PureWhite,
)

private val DarkColorScheme = darkColorScheme(
    primary = Crimson300,
    onPrimary = Slate950,
    primaryContainer = Crimson900Muted,
    onPrimaryContainer = Crimson100,
    secondary = Teal300,
    onSecondary = Slate950,
    secondaryContainer = Teal900Muted,
    onSecondaryContainer = Teal100,
    background = Slate950,
    onBackground = Slate100,
    surface = Slate950,
    onSurface = Slate100,
    surfaceVariant = Slate800,
    onSurfaceVariant = Slate200,
    outline = Slate700,
    error = ErrorDark,
    onError = Slate950,
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
