package android.skills_market.presentation
import android.skills_market.ResumeColumn
import android.skills_market.Cap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ResumeScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Cap()
        ResumeColumn()
    }
}