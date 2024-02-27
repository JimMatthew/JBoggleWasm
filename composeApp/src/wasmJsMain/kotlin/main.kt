
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow

@OptIn(ExperimentalComposeUiApi::class)
fun main() {


    val state = StateManager()



    CanvasBasedWindow(canvasElementId = "ComposeTarget") { App(state) }

}







