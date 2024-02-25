import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.random.Random

class BoggleBoard
    (
    private val gameOver: (Boolean) -> Unit,
    private val wordInput: (String) -> Unit,
    private val pressedDice: (ArrayList<Int>) -> Unit,
    private val boardArray: (Array<String>) -> Unit,
    private val updateStatus: (String) -> Unit,
    private val isHighScoreMode: (Boolean) -> Unit,
    private val updateTime: (String) -> Unit,

    ) {

    private var currentWord = ""
    private val die = arrayOf(
        "aaeegn", "elrtty", "abbjoo", "abbkoo", "ehrtvw", "cimotu", "distty", "eiosst", "achops",
        "himnqu", "eeinsu", "eeghnw", "affkps", "hlnnrz", "deilrx", "delrvy"
    )

    var board = Array(16) { "" }

    //private var highScoreHandler: BoggleWordHandler = BoggleWordHandler()
    var dictSet = HashSet<String>()
    private val tsolver = BoggleTrieSolver()
    private var SIZE = 4
    private var time = 0
    private var playTime = 100

    //private var timer: Timer? = null
    private var isGameOver = true
    private var isRandom = true
    private val genScore = 200
    private var isRunning = false
    private var HSIndex = 0
    private val gameBoardList: MutableList<Array<String>> = ArrayList()
    private val gameBoardWordList: MutableList<List<String>> = ArrayList()
    var pressed = ArrayList<Int>()
    val timerScope = CoroutineScope(Dispatchers.Default)
    var isDictLoaded = false
    val timerJob = null
    var isplaying = false

    enum class InputType {
        TAP,
        DRAG
    }

    fun startNewGame() {
        //if (timer != null) timer!!.cancel()
        isplaying = false
        isGameOver = false
        gameOver(false)
        board = if (isRandom) {
            rollDice(die)
        } else {
            if (HSIndex + 5 >= gameBoardList.size) {
                makeHighScoreBoards()
            }
            gameBoardList[HSIndex++]
        }
        clearCurrentWord()
        pressed = ArrayList()
        pressedDice(pressed)
        updateStatus("")
        boardArray(board)
        isplaying = true
        startTimer()
    }

    fun loadDict(dict: List<String>) {
        tsolver.loadWordList(dict)
        //makeHighScoreBoards()
    }

    private fun startTimer() {
        time = playTime
        updateTime(time.toString())
        val timerScope = CoroutineScope(Dispatchers.Default)
        val timerJob = timerScope.launch {
            var count = 0
            while (isplaying && time > 0) {
                delay(1000)
               incrementTime()
                //updateVariable(count)
            }

        }

        //timerJob.cancel()
    }

    private fun timerStop() {
        isplaying = false
    }

    fun incrementTime() {
        if (time-- > 0) {
            updateTime(time.toString())
        } else {
            isGameOver = true
            gameOver(true)
            updateStatus("")
            isplaying= false
        }
    }

    fun getPressed(): List<Int> {
        return ArrayList(pressed)
    }

    fun clearCurrentWord() {
        pressed = ArrayList()
        currentWord = ""
        pressedDice(pressed)
        wordInput(currentWord)
    }

    fun useHighScoreBoards() {
        isRandom = !isRandom
        isHighScoreMode(!isRandom)
    }

    fun letterPress(button: Int, type: InputType) {
        if (button < 0 || button >= SIZE * SIZE) return
        if (isGameOver) return
        if (pressed.contains(button)) {
            setNewPosition(pressed.indexOf(button), type)
            currentWord = currentWord.substring(0, pressed.size)
        } else if (isNextTo(button)) {
            currentWord += board[button]
            pressed.add(button)
        }
        wordInput(currentWord)
        pressedDice(pressed)
        boardArray(board)
        updateStatus("")
    }

    fun isHighScore(): Boolean {
        return !isRandom
    }

    private fun makeHighScoreBoards() {
        if (false) {

            isRunning = true
            val size = gameBoardList.size + 10
            while (gameBoardList.size < size) {
                val b = rollDice(die)
                val words = tsolver.solve(b)
                if (words.size > genScore) {
                    gameBoardList.add(b)
                    gameBoardWordList.add(words)
                }
            }
            isRunning = false
        }
    }

    private fun setNewPosition(position: Int, type: InputType) {
        val newPosition = if (type == InputType.DRAG) position + 1 else position
        pressed = pressed.take(newPosition).toMutableList() as ArrayList<Int>
    }

    /*
        Check if a button is next to the last pressed button
        If there are no pressed buttons, return true
     */
    private fun isNextTo(button: Int): Boolean {
        if (pressed.size == 0) return true
        val last = pressed[pressed.size - 1]
        val Xbtt = button / SIZE
        val Ybtt = button % SIZE
        val Xblp = last / SIZE
        val Yblp = last % SIZE
        return abs(Xbtt - Xblp) <= 1 && abs(Ybtt - Yblp) <= 1
    }

    /*
        We need to select a Random letter from each die, then shuffle the order
        of those randomly selected letters
     */
    private fun rollDice(die: Array<String>): Array<String> {
        val random = Random.Default
        return die.map { it[random.nextInt(it.length)].toString() }.shuffled().toTypedArray()
    }

    init {
        pressed = ArrayList()
        makeHighScoreBoards()
    }
}
