package views

import BoggleBoard
import androidx.compose.foundation.Image


import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BoardDisplay(
    pressed: List<Int>,
    board: Array<String>,
    pressLetter: (Int, Enum: BoggleBoard.InputType) -> Unit
) {
    var touchPoint by remember { mutableStateOf(Offset.Zero) }
    var xoff by remember { mutableDoubleStateOf(0.0) }
    var yoff by remember { mutableDoubleStateOf(0.0) }
    var endDragIndex by remember { mutableIntStateOf(-1) }
    val screenWidth = 500
    val boxsize = (screenWidth) / 4
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

                        ) {
                        Box(
                            modifier = Modifier
                                .size(boxsize.dp)
                                .fillMaxSize()
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            try {
                                                touchPoint = it
                                                pressLetter(index, BoggleBoard.InputType.TAP)
                                            } finally {
                                            }
                                        },
                                    )
                                }
                                .pointerInput(Unit) {
                                    detectDragGestures(
                                        onDragStart = {
                                            touchPoint = it
                                            endDragIndex = index
                                            //save the position the pointer is inside the button
                                            xoff =
                                                j + ((touchPoint.x) / (swp / 4)).toDouble()
                                            yoff =
                                                i + ((touchPoint.y) / (swp / 4)).toDouble()
                                        },
                                        onDrag = { change, dragAmount ->
                                            change.consume()
                                            val a = swp / 4
                                            xoff += dragAmount.x / a
                                            yoff += dragAmount.y / a
                                            val x = endDragIndex % 4    //button currently over
                                            val y = endDragIndex / 4

                                            if (yoff >= 0 && yoff < 4) {
                                                val tx = xoff % 1   //position inside button
                                                val ty = yoff % 1
                                                if ((tx > .15 && tx < .85) && (ty > .15 && ty < .85)){
                                                    if (xoff.toInt() != x && yoff.toInt() != y) {
                                                        endDragIndex = xoff.toInt() + (yoff.toInt() * 4)
                                                        pressLetter(endDragIndex, BoggleBoard.InputType.DRAG)
                                                    } else if (xoff.toInt() != x) {
                                                        endDragIndex = xoff.toInt() + y * 4
                                                        pressLetter(endDragIndex, BoggleBoard.InputType.DRAG)
                                                    } else if (yoff.toInt() != y) {
                                                        endDragIndex = (yoff.toInt() * 4) + x
                                                        pressLetter(endDragIndex, BoggleBoard.InputType.DRAG)
                                                    }
                                                }
                                            }
                                        },
                                        onDragEnd = {
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Image(
                                painter = painterResource("/blank.png"),
                                contentDescription = null
                            )

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
}