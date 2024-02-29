


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BoardDisplay2(
    pressed: List<Int>,
    board: Array<String>,
    pressLetter: (Int, Enum: BoggleBoard.InputType) -> Unit
) {
    var touchPoint by remember { mutableStateOf(Offset.Zero) }
    var xoff by remember { mutableDoubleStateOf(0.0) }
    var yoff by remember { mutableDoubleStateOf(0.0) }
    var endDragIndex by remember { mutableIntStateOf(-1) }

    val screenWidth = 500
    var boxsize = (screenWidth) / 4
    //var swp = 400
    val swp = with(LocalDensity.current) { screenWidth.dp.toPx() }
    Column {
        for (i in 0..3) {
            Row {
                for (j in 0..3) {
                    val index = (i * 4) + j
                    Button(
                        onClick = {
                        },
                        shape = RectangleShape,
                        modifier = Modifier

                            .size(boxsize.dp) // Adjust size as needed
                            .padding(3.dp), // Adjust padding as needed
                        colors = ButtonDefaults.buttonColors(Color.White),
                        contentPadding = PaddingValues(0.dp),
                        )
                    {


                        Text(
                            text = board[index].uppercase(),
                            color = if (pressed.contains(index)) Color(0xFFce6f1b) else Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp
                        )


                    }
                }
            }
        }
    }
}