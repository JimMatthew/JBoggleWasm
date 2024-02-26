

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Controls(
    numWords: Int,
    score: Int,
    wordsOnBoard: List<String?>,
    status: String,
    isHS: Boolean,
    submit: () -> Unit,
    toggleHS: () -> Unit,
    cancel: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.width(500.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            onClick = {
                submit()
            }) {
            Text("Submit", color =Color.White )
        }
        Button(
            colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            onClick = {
                cancel()
            }) {
            Text("Clear", color =Color.White )
        }
    }
    Column {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = status,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
        Text(
            text = "Words Found: $numWords",
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        )
        Text(
            text = "Score: $score",
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        )
        Text(
            text = "Words on Board: " + wordsOnBoard.size,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Toggle(
                text = "High Score Mode \n(only boards with over 200 words)",
                value = isHS,
                onValueChanged = { toggleHS() })
        }
    }
}

@Composable
fun Toggle(
    text: String,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    Row {
        Text(text)
        Checkbox(checked = value, onCheckedChange = onValueChanged)
    }
}