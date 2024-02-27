package com.example.boggle24.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    timeleft: String,
    currentWord: String,
    newGame: () -> Unit,
) {

    val size = 300

    Column {

        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Boggle",
                modifier = Modifier.width((300).dp).padding(bottom = 80.dp),
                fontSize = 40.sp,
                fontFamily = FontFamily.SansSerif
            )
        }

        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "  Time : $timeleft",
                modifier = Modifier.padding(20.dp).width((180).dp),
                fontSize = 30.sp
            )
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                modifier = Modifier.padding(5.dp).width(250.dp),
                onClick = {
                    newGame()
                }) {
                Text("New Game", color = Color.White)
            }
        }
        Row(
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ){
            Text(
                text = currentWord.uppercase(),
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 29.sp
                )
            )
        }
    }
}