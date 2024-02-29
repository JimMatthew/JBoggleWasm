
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yahtzee.kotlin.Rules
import yahtzee.kotlin.diceView


class DiceGameManager {
    private val diceRoller = DiceRoller()
    var numScore = mutableStateOf("")
    var bonus = mutableStateOf(" ")
    var chance = mutableStateOf("")
    var gamedice = mutableStateOf(diceRoller.getDice())
    var roll = mutableStateOf(1)
    var totalScore = mutableStateOf("")
    var isScored = mutableStateOf(false)
    var isOver = mutableStateOf(false)
    var isScoreAdded = mutableStateOf(false)
    val pressed = mutableStateOf(diceRoller.getKeep())
    val scores = mutableStateOf(HashMap<ScoreTypes, Int?>())
    val displayMap = mutableStateOf(HashMap<ScoreTypes, Int?>())

    @Composable
    fun startGame() {

        val colorpressed = ButtonDefaults.buttonColors(Color(0xFFce6f1b))
        val colornormal = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Row {

                numbersView(
                    total = numScore.value,
                    bonus = bonus.value,
                    Press = { doScore(it) },
                    hasScore = { hasScore(it) },
                    display = displayMap.value
                )

                gameScores(
                    display = displayMap.value,
                    Press = { doScore(it) },
                    hasScore = { hasScore(it) }
                )

                Column {
                    Text(
                        "Total: ${totalScore.value}",
                        modifier = Modifier.width(150.dp).padding(10.dp),
                        fontSize = 20.sp
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        onClick = { newGame() }
                    ) {
                        Text("Start Game", color = Color.White)
                    }
                    Button(
                        //#3297d3
                        colors = ButtonDefaults.buttonColors(Color(0xFF3287d3)),
                        onClick = { rollDice() }
                    ) {
                        Text("Roll", color = Color.White)
                    }
                    Text("Roll: ${roll.value}", modifier = Modifier.width(80.dp), fontSize = 20.sp)
                }
            }

            diceView(
                pressed = pressed.value,
                gameDice = gamedice.value,
                diePressed = { diePressed(it) }
            )
        }
    }

    private fun rollDice() {
        if (!isScored.value) {
            isScoreAdded.value = false
            if (isOver.value) {
                roll.value = 0
                isOver.value = false
            }
            if (roll.value < 4) {
                diceRoller.roll()
                gamedice.value = diceRoller.getDice()
                displayCurrentScores()
                roll.value++
            }
            if (roll.value == 3) {
                isScored.value = true
                isOver.value = true
            }
            pressed.value = diceRoller.getKeep()
        }
    }

    val dice: IntArray
        get() = diceRoller.getDice()

    private fun holdDie(die: Int) {
        diceRoller.keep(die)
    }

    private fun unholdDie(die: Int) {
        diceRoller.unkeep(die)
    }

    private fun newGame() {
        isScored.value = false
        diceRoller.newTurn()
        diceRoller.roll()
        gamedice.value = diceRoller.getDice()
        roll.value = 1
        resetScore()
        totalScore.value = TotalScore.toString()
        displayCurrentScores(0)
        numScore.value = totalTop.toString()
        bonus.value = getbonus.toString()
        pressed.value = diceRoller.getKeep()
    }

    private fun newRoll() {
        isScored.value = false
        diceRoller.newTurn()
        totalScore.value = TotalScore.toString()
    }

    private fun displayCurrentScores() {
        for (type in ScoreTypes.entries) {
            if (!hasScore(type)) {
                displayMap.value[type] = Rules.getScore(type, dice)
            }
        }
    }

    private fun displayCurrentScores(score: Int) {
        for (type in ScoreTypes.entries) {
            if (!hasScore(type)) {
                displayMap.value[type] = score
            }
        }
    }

    private fun doScore(type: ScoreTypes?) {
        if (!isScoreAdded.value && !hasScore(type!!)) {
            setScore(type, Rules.getScore(type, dice))
            newRoll()
            numScore.value = totalTop.toString()
            bonus.value = getbonus.toString()
            isOver.value = true
            isScoreAdded.value = true
        }
    }

    private fun UpdateScoreType(type: ScoreTypes, num: Int) {
        displayMap.value[type] = num
    }

    private fun diePressed(die: Int) {
        if (pressed.value[die] == 0) {
            holdDie(die)
        } else if (pressed.value[die] == 1) {
            unholdDie(die)
        }
        pressed.value = diceRoller.getKeep()
    }

    private fun getScore(type: ScoreTypes): Int {
        return if (scores.value[type] != null) {
            scores.value[type]!!
        } else {
            0
        }
    }

    private fun hasScore(type: ScoreTypes): Boolean {
        return if (scores.value[type] != null) {
            true
        } else {
            false
        }
    }

    private val TotalScore: Int
        get() {
            var s = 0
            for ((_, value) in scores.value) {
                if (value != null) {
                    s += value
                }
            }
            if (totalTop >= 62) {
                s += 35
            }
            return s
        }

    private fun resetScore() {
        for(score in ScoreTypes.entries){
            scores.value[score] = null
        }
    }

    private fun setScore(type: ScoreTypes, score: Int) {
        scores.value[type] = score
    }

    private val totalTop: Int
        get() {
            var t = 0
            t += getScore(ScoreTypes.one)
            t += getScore(ScoreTypes.two)
            t += getScore(ScoreTypes.three)
            t += getScore(ScoreTypes.four)
            t += getScore(ScoreTypes.five)
            t += getScore(ScoreTypes.six)
            return t
        }

    private val getbonus: Int
        get() = if (totalTop >= 62)  35  else  0

}