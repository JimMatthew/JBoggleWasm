class BoggleWordHandler {
    private val wordSet: HashSet<String> = HashSet()
    private val tsolver = BoggleTrieSolver()



    fun solveBoard(board: Array<String>): List<String> {
        return tsolver.solve(board)
    }
}
