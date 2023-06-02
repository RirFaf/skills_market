package android.skills_market.ui.activities.screens.custom_composables

import android.skills_market.ui.theme.Gray150
import android.skills_market.data.ResumeModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumeCard(item:ResumeModel){
    Row(
        modifier = Modifier
            //.background(color = gray)
            .fillMaxWidth()
            .padding(horizontal = 14.dp,vertical=4.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Gray150
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 30.dp),
            ){
                Text(
                    text = item.name,
                    style = TextStyle(
                        fontSize = 18.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text(text= "От"+ " " + item.minprice+ " " + "до"+ " "  +item.maxprice)
                Text(text= "Специальность: "+ item.profession)
            }
        }
    }
}
