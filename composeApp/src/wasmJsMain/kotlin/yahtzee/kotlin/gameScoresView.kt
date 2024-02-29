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
fun gameScores(
    display: Map<ScoreTypes, Int?>,
    Press: (ScoreTypes) -> Unit,
    hasScore: (ScoreTypes) -> Boolean
){
    val colornormal = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
    val colorpressed = ButtonDefaults.buttonColors(Color(0xFFce6f1b))
    Column {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)){
            Text("3 of a Kind: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.threeKind)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.threeKind) }
            ) {
                Text(display[ScoreTypes.threeKind]?.toString() ?: "0")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)) {
            Text("4 of a Kind: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.fourKind)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.fourKind) }
            ) {
                Text(display[ScoreTypes.fourKind]?.toString() ?: "0")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)) {
            Text("Full House: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.fullHouse)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.fullHouse) }
            ) {
                Text(display[ScoreTypes.fullHouse]?.toString() ?: "0")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)) {
            Text("sm. Straight: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.smallStraight)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.smallStraight) }
            ) {
                Text(display[ScoreTypes.smallStraight]?.toString() ?: "0")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)) {
            Text("lg. Straight: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.largeStraight)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.largeStraight) }
            ) {
                Text(display[ScoreTypes.largeStraight]?.toString() ?: "0")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)) {
            Text("Yahtzee: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.Yahtzee)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.Yahtzee) }
            ) {
                Text(display[ScoreTypes.Yahtzee]?.toString() ?: "0")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp, 5.dp)) {
            Text("Chance: ", modifier = Modifier.width(80.dp))
            OutlinedButton(
                colors = if (hasScore(ScoreTypes.Chance)) colorpressed else colornormal,
                onClick = { Press(ScoreTypes.Chance) }
            ) {
                Text(display[ScoreTypes.Chance]?.toString() ?: "0")
            }
        }
    }
}