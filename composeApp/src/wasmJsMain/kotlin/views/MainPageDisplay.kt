package views

import DiceGameManager
import StateManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun mainPage() {

    var boggleState = remember { mutableStateOf(StateManager()) }
    var currentPage = remember { mutableStateOf(Page.HOME) }
    var yahtzeeState = remember { mutableStateOf(DiceGameManager()) }
    Scaffold (topBar = {
        TopAppBar(
            backgroundColor = Color(0xFF3287d3),
            contentColor = Color.White,
            title = {
                Text("Jam World")
            }
        )
    },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color(0xFF3287d3),
                contentColor = Color.White,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "2024 JML Enterprises",
                )
            }
        },
    ) { innerPadding ->
        Row {
            Column (modifier = Modifier.width(100.dp)){
                NavigationRailSample { currentPage.value = it }

            }
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (currentPage.value == Page.HOME){
                    MainText()
                }
                if (currentPage.value == Page.BOGGLE){
                    boggleState.value.StateManager()
                }
                if (currentPage.value == Page.YAHTZEE){
                    yahtzeeState.value.startGame()
                }
            }
        }

    }
}

enum class Page(val title:String, val content: String){
    HOME("home",""),
    BOGGLE("Boggle",""),
    YAHTZEE("Yahtzee","")
}

@Composable
fun MainText() {
    Text(
        modifier = Modifier.padding(8.dp),
        text =
        """
            
                    This page was written entirely in Kotlin and then compiled into webassembly.
                    
                    This page will be a work in progress as I play around with learning Kotlin and Compose. 
                    
                    
                    
                    Check out some of my projects with the links on the left
                    
                """.trimIndent(),
    )
}
@Composable
fun NavigationRailSample(changePage: (Page) -> Unit) {
    var selectedItem by remember { mutableStateOf(0) }
    val pages = Page.entries.toTypedArray()
    val icons = listOf(Icons.Filled.Home, Icons.Filled.PlayArrow, Icons.Filled.Settings)
    Row {
        NavigationRail {
            pages.forEachIndexed { index, item ->
                when(item){
                    Page.HOME -> {
                        NavigationRailItem(
                            label = { Text("Home") },
                            icon = { Icon(icons[index], contentDescription = "") },
                            selected = false,
                            onClick = { selectedItem = index
                            changePage(item)}
                        )
                    }
                    Page.BOGGLE -> {
                        NavigationRailItem(
                            label = { Text("Boggle") },
                            icon = { Icon(icons[index], contentDescription = "") },
                            selected = false,
                            onClick = { selectedItem = index
                                changePage(item)}
                        )
                    }
                    Page.YAHTZEE -> {
                        NavigationRailItem(
                            label = { Text(item.title) },
                            icon = { Icon(icons[index], contentDescription = "") },
                            selected = false,
                            onClick = { selectedItem = index
                                changePage(item)        },
                        )
                    }
                }
            }
        }
        Text(pages[selectedItem].content, Modifier.padding(start = 8.dp))
    }
}