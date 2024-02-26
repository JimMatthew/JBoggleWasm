

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameOverDisplay(
    numWords: String,
    score: Int,
    foundWords: String,
    wordsOnBoard: List<String?>,
    showBoard: () -> Unit
) {

    Column {
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    showBoard()
                }) {
                Text("Show Board", color =Color.White )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Game Over!",
                color = Color.Black, // Adjust text color as needed
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "Words Found: $numWords",
                color = Color.Black, // Adjust text color as needed
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Score: $score",
                color = Color.Black, // Adjust text color as needed
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
        }
    }

    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        val size = wordsOnBoard.size
        TextField(
            value = wordsOnBoard.joinToString(separator = "\n").uppercase(),
            onValueChange = { },
            label = { Text("$size Words on Board") },
            maxLines = 30,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            //modifier = Modifier.padding(20.dp),
            modifier = Modifier
               // .weight(1f)
                .padding(10.dp),
            readOnly = true
        )
        TextField(
            value = foundWords.uppercase(),
            onValueChange = { },
            label = { Text("Words Found") },
            maxLines = 30,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            ),

            modifier = Modifier
                //.weight(1f)
                .padding(10.dp),
            readOnly = true
        )
    }

}