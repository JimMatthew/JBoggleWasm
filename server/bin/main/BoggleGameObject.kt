import kotlinx.serialization.Serializable

@Serializable
data class BoggleGame(val id: String, val name: String, val score: Int, val wordsFound: Int)