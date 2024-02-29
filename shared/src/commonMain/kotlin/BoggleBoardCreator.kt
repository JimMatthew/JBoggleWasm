import kotlin.random.Random


fun CreateBoard(): Array<String> {
    val die = arrayOf(
        "aaeegn", "elrtty", "abbjoo", "abbkoo", "ehrtvw", "cimotu", "distty", "eiosst", "achops",
        "himnqu", "eeinsu", "eeghnw", "affkps", "hlnnrz", "deilrx", "delrvy"
    )

    val random = Random.Default
    return die.map { it[random.nextInt(it.length)].toString() }.shuffled().toTypedArray()
}