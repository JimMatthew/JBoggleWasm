

class WordScoreHandlero (
    var updateStatus: (String) -> Unit,
    var updateScore: (Int) -> Unit,
    var updateNumWordsFound: (Int) -> Unit,
    var updateWordsFound: (String) -> Unit,
    var allWordsOnBoard: (List<String>) -> Unit
){
     var wordSet: HashSet<String> = HashSet()
    private val userWordSet: MutableSet<String>
    private var wordsOnBoard: List<String> = ArrayList()
    private val tsolver: BoggleTrieSolver
    private var score = 0
    init {
        userWordSet = HashSet()
        tsolver = BoggleTrieSolver()
    }


    fun loadWordList(set: HashSet<String>) {
        wordSet = set
    }

    private fun userWordsString(): String {
        return userWordSet.joinToString(separator = "\n")
    }

    fun setBoardLayout(board: Array<String>) {
        wordsOnBoard = tsolver.solve(board)
        allWordsOnBoard(wordsOnBoard)
    }


    private fun testIfWordExists(word: String): Boolean {
        return wordSet.contains(word.lowercase())
    }

    fun submit(word: String) :Boolean{
        return if (!testIfWordExists(word)) {
            updateStatus("${word.uppercase()}  Is Not a Word!")
            false
        } else if (testIfWordAlreadyFound(word)) {
            updateStatus("${word.uppercase()}  Was Already Found!")
            false
        } else {
            userWordSet.add(word.lowercase())
            updateStatus("${word.uppercase()} Was Found!")
            score += computeScore(word)
            updateScore(score)
            updateNumWordsFound(userWordSet.size)
            updateWordsFound(userWordsString())
            true
        }
    }

    private fun testIfWordAlreadyFound(word: String): Boolean {
        return userWordSet.contains(word.lowercase())
    }

    fun clearUserWords() {
        userWordSet.clear()
        score = 0
        updateScore(score)
        updateWordsFound(userWordsString())
    }

    private fun computeScore(word: String): Int {
        return when (val len = word.length) {
            3, 4 -> 1
            5 -> 2
            6 -> 3
            7 -> 5
            else -> if (len > 7) 11 else 0
        }
    }
}
