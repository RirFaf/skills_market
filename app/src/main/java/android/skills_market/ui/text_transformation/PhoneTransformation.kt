package android.skills_market.ui.text_transformation

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

class PhoneTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return phoneFilter(text)
    }
}

fun phoneFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 11) text.text.substring(0..10) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
//        if (i % 2 == 1 && i < 4) out += "."
        when (i) {
            0 -> {
                out = "+$out("
            }

            3 -> {
                out += ")"
            }

            6 -> {
                out += "-"
            }

            8 -> {
                out += "-"
            }
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 3) return offset + 2
            if (offset <= 6) return offset + 3
            if (offset <= 8) return offset + 4
            if (offset <= 11) return offset + 5
            return 16
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 2) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 10) return offset - 3
            if (offset <= 13) return offset - 4
            if (offset <= 16) return offset - 5
            return 11
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

@Preview
@Composable
fun PreviewTextField() {
    var value by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 11) {
                value = it
            }
            Log.d("MyPrev", value)
        },
        visualTransformation = PhoneTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}