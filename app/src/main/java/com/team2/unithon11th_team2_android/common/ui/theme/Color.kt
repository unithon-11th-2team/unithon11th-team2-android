package com.team2.unithon11th_team2_android.common.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Primary = Color(0xFFD5A458)
val Black = Color(0xFF131313)
val Gray = Color(0xFFEDECEA)
val Red = Color(0xFFC4162D)
val White = Color(0xFFFFFFFF)

@Immutable
data class OurColorScheme(
    val primary: Color = Primary,
    val black: Color = Black,
    val gray: Color = Gray,
    val red: Color = Red,
    val white: Color = White
)

val OurColorPalette = staticCompositionLocalOf { OurColorScheme() }