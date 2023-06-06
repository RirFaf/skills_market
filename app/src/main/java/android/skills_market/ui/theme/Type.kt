package android.skills_market.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    headlineLarge = TextStyle(
        color = Color.Black,
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.SansSerif
    ),
    //button
    bodyMedium = TextStyle(
        color = Color.White,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)