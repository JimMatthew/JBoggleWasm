package yahtzee.kotlin

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun diceView(
    pressed: IntArray,
    gameDice: IntArray,
    diePressed: (Int) -> Unit
) {
    val colorPressed = ButtonDefaults.buttonColors(Color(0xFFce6f1b))
    val colorNormal = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))

    Row {
        for (i in 0 ..4){
            Button(
                modifier = Modifier.size(60.dp),
                onClick = { diePressed(i) },
                colors = if (pressed[i] == 0) colorNormal else colorPressed
            ) {
                Text(gameDice[i].toString(), fontSize = 25.sp)
            }
        }
    }
}