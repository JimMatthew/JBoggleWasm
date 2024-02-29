import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun numbersView(
    total: String,
    bonus: String,
    Press:(ScoreTypes) -> Unit,
    display: Map<ScoreTypes, Int?>,
    hasScore: (ScoreTypes) -> Boolean
) {
    val colornormal = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
    val colorpressed = ButtonDefaults.buttonColors(Color(0xFFce6f1b))

    Column {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("One: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.one)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.one) }
            ) {
                val v = display[ScoreTypes.one]?.toString() ?: "0"
                Text(v)
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Two: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.two)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.two) }
            ) {
                Text(display[ScoreTypes.two]?.toString() ?: "0")
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Three: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.three)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.three) }
            ) {
                Text(display[ScoreTypes.three]?.toString() ?: "0")
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Four: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.four)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.four) }
            ) {
                Text(display[ScoreTypes.four]?.toString() ?: "0")
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Five: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.five)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.five) }
            ) {
                Text(display[ScoreTypes.five]?.toString() ?: "0")
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Six: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.six)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.six) }
            ) {
                Text(display[ScoreTypes.six]?.toString() ?: "0")
            }
        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Total: $total", modifier = Modifier.width(80.dp))

        }
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("Bonus: $bonus", modifier = Modifier.width(80.dp))

        }
    }
}