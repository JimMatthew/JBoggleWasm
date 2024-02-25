
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun home() {

    Column {
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = "  Jam Home ",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 30.sp
                )
                Text(
                    text = "This site is built with Kotlin and Webassembly ",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 20.sp
                )
            }
        }

        Row {
            Column {
                Text(
                    text = "Pages",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 15.sp
                )
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f),
                    onClick = {

                    }) {
                    Text("Submit")
                }
            }
        }
    }

}