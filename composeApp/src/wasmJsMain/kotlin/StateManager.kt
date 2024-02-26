import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bogglegame.WordScoreHandler
import com.example.boggle24.ui.theme.Header
import kotlinx.browser.window

class StateManager() {

    private val boardMaker = BoggleBoard(
        gameOver = { gameOver(it) },
        wordInput = { currentWord(it) },
        pressedDice = { PressedDice(it) },
        boardArray = { BoardArray(it) },
        updateStatus = { gameStatus(it) },
        isHighScoreMode = { isHighScoreMode(it) },
        updateTime = { setTimeLeft(it) }
    )

    private var timeLeft = mutableStateOf("0")
    private var gameover = mutableStateOf(false)
    private var current = mutableStateOf(" ")
    private var pressed = mutableStateOf(ArrayList<Int>())
    var board = mutableStateOf(boardMaker.board)
    private val numWordsFound = mutableIntStateOf(0)
    private val foundWords = mutableStateOf("")
    private var wordsOnBoard = mutableStateOf(ArrayList<String>())
    private var status = mutableStateOf("")
    private var score = mutableIntStateOf(0)
    private var isHighScore = mutableStateOf(boardMaker.isHighScore())
    private var showBoardAfterGame = mutableStateOf(false)
    var isDictLoaded = mutableStateOf(false)

    val boggleWordHandler = WordScoreHandler(
        updateStatus = { gameStatus(it) },
        updateScore = { score(it) },
        updateNumWordsFound = { NumWordsFound(it) },
        updateWordsFound = { foundWords(it) },
        allWordsOnBoard = { wordsOnBoard(it) }
    )

    @Composable
    fun StateManager() {

        if (!isDictLoaded.value) {
            loadDict() {
                upd(it)
            }
            Row(
                horizontalArrangement = Arrangement.Absolute.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = " Loading... ",
                    modifier = Modifier.padding(1.dp).width((500).dp),
                    fontSize = 20.sp
                )
            }
        }

        Header(
            timeleft = timeLeft.value,
            currentWord = current.value,
            newGame = { startNewGame() }
        )

        if (showBoardAfterGame.value || !gameover.value) {
            BoardDisplay(
                board = board.value,
                pressed = pressed.value
            ) { index, type ->
                boardMaker.letterPress(index, type)
            }
        }

        if (!gameover.value) {

            Controls(
                numWords = numWordsFound.intValue,
                score = score.intValue,
                wordsOnBoard = wordsOnBoard.value,
                status = status.value,
                isHS = isHighScore.value,
                submit = { submitWord() },
                toggleHS = { boardMaker.useHighScoreBoards() },
                cancel = { boardMaker.clearCurrentWord() }
            )
        } else {
            GameOverDisplay(
                numWords = numWordsFound.intValue.toString(),
                score = score.intValue,
                foundWords = foundWords.value,
                wordsOnBoard = wordsOnBoard.value,
                showBoard = { showBoard() }
            )
        }
    }

    fun showBoard() {
        showBoardAfterGame.value = !showBoardAfterGame.value
    }

    private fun setTimeLeft(time: String) {
        timeLeft.value = time
    }

    private fun gameOver(over: Boolean) {
        gameover.value = over
    }

    private fun currentWord(currentWord: String) {
        current.value = currentWord
    }

    private fun PressedDice(pressedDice: ArrayList<Int>) {
        pressed.value = pressedDice
    }

    private fun BoardArray(boardArray: Array<String>) {
        board.value = boardArray
    }

    private fun NumWordsFound(numWordsFound: Int) {
        this.numWordsFound.intValue = numWordsFound
    }

    private fun foundWords(foundWords: String) {
        this.foundWords.value = foundWords
    }

    private fun wordsOnBoard(wordsOnBoard: List<String>) {
        this.wordsOnBoard.value = wordsOnBoard as ArrayList<String>
    }

    private fun gameStatus(status: String) {
        this.status.value = status
    }

    fun score(score: Int) {
        this.score.intValue = score
    }

    private fun isHighScoreMode(mode: Boolean) {
        isHighScore.value = mode
    }

    private fun startNewGame() {
        boardMaker.startNewGame()
        boggleWordHandler.clearUserWords()
        boggleWordHandler.setBoardLayout(board.value)
    }

    fun upd(d: String) {
        //gameStatus(d)
        val words = d.trimIndent().split("\n")
        UpdateDict(words)
    }

    private fun submitWord() {
        if (current.value.length < 3) return
        if (boggleWordHandler.submit(current.value.lowercase())) {

        }
        boardMaker.clearCurrentWord()
    }

    fun UpdateDict(dict: List<String>) {
        boggleWordHandler.loadDict(dict)
        boardMaker.loadDict(dict)
        isDictLoaded.value = true
    }
}


private fun loadDict(up: (String) -> Unit) {

    val output = HashSet<String>()

    window.fetch(
        "/JoggleWasm/enable1.txt",
    )
        .then { it ->
            if (it.ok) {
                it.text().then {
                    up(it.toString())
                    null
                }
            } else {
                output.add(it.statusText)
            }
            null
        }
        .catch {
            output.add("error")
            null
        }
}

external interface dictlist {
    val dict: String?
}



