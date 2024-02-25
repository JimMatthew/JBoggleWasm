package com.example.boggle24.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
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
        ) {
            Text(
                text = "  Time : $timeleft",
                modifier = Modifier.padding(1.dp).width((size).dp),
                fontSize = 20.sp
            )
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                modifier = Modifier.padding(5.dp).width(size.dp),
                onClick = {
                    newGame()
                }) {
                Text("New Game")
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