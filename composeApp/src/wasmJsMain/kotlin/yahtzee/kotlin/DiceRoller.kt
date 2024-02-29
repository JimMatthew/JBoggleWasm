import kotlin.random.Random

class DiceRoller {
    private var dice = IntArray(5)
    private var diekeep = IntArray(5)

    init {
        dice = diekeep
    }

    fun roll() {
        for (i in 0..4) {
            if (diekeep[i] == 0) {
                val random = Random.Default
                dice[i] = random.nextInt(6)+1
            }
        }
    }

    fun keep(i: Int) {
        diekeep[i] = 1
    }

    fun unkeep(i: Int) {
        diekeep[i] = 0
    }

    fun keep(i: IntArray) {
        diekeep = i
    }

    fun newTurn() {
        val k = intArrayOf(0, 0, 0, 0, 0)
        keep(k)
    }

    fun getDice(): IntArray {
        return dice.copyOf()
    }

    fun getKeep(): IntArray{
        return diekeep.copyOf()
    }
}
