
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(
         state: StateManager,
        ) {
    MaterialTheme {
        //var w = mutableStateOf(ws)
        var showContent by remember { mutableStateOf(false) }
        val greeting = remember { Greeting().greet() }

        //home()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            //var state = StateManager(ws)
            state.StateManager()
            //state.setWordSet(w.value)
            //HomePage()
        }
    }

}