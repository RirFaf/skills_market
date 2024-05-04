package android.skills_market.ui.text_transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class SNILSTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return snilsFilter(text)
    }
}

fun snilsFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 11) text.text.substring(0..10) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 2 || i == 5) out += "-"
        if (i == 8) out += " "
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int =
            when (offset) {
                in (1..3) -> offset
                in (4..6) -> offset + 1
                in (7..9) -> offset + 2
                in (10..11) -> offset + 3
                else -> offset
            }


        override fun transformedToOriginal(offset: Int): Int =
            when (offset) {
                in (1..3) -> offset
                in (4..7) -> offset-1
                in (8..11) -> offset-2
                in (12..14) -> offset-3
                else -> offset
            }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}