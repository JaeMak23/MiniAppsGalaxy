package com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Board
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Direction
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.MoveResult
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Tile
import kotlin.random.Random

class GameUseCase(
    private val random: Random = Random.Default,
    private val boardSize: Int = 4,
) {
    private var nextTileId = 0L

    fun initBoard(): Board {
        var board = Board(size = boardSize)
        repeat(2) { board = spawnRandomTile(board) }
        return board
    }

    fun move(board: Board, direction: Direction): MoveResult {
        val (shiftedGrid, scoreGained, moved) = shiftAndMerge(board, direction)

        if (!moved) {
            return MoveResult(
                board = board,
                scoreGained = 0,
                moved = false,
                isGameOver = isGameOver(board),
                isWin = false,
            )
        }

        val boardAfterMove = gridToBoard(shiftedGrid, board.size)
        val boardWithSpawn = spawnRandomTile(boardAfterMove)
        val isWin = shiftedGrid.any { row -> row.any { it == WIN_VALUE } }

        return MoveResult(
            board = boardWithSpawn,
            scoreGained = scoreGained,
            moved = true,
            isGameOver = isGameOver(boardWithSpawn),
            isWin = isWin,
        )
    }

    private fun shiftAndMerge(
        board: Board,
        direction: Direction,
    ): Triple<Array<IntArray>, Int, Boolean> {
        val size = board.size
        val grid = boardToGrid(board)
        val rotated = rotateForDirection(grid, direction)

        var totalScore = 0
        var anyMoved = false

        val result = Array(size) { rowIndex ->
            val row = rotated[rowIndex]
            val (mergedRow, score, rowMoved) = mergeLineLeft(row)
            totalScore += score
            if (rowMoved) anyMoved = true
            mergedRow
        }

        val unrotated = unrotateForDirection(result, direction)
        return Triple(unrotated, totalScore, anyMoved)
    }

    /** Slides non-zero values left, merges equal neighbors once, pads with zeros. */
    private fun mergeLineLeft(line: IntArray): Triple<IntArray, Int, Boolean> {
        val nonZero = line.filter { it != 0 }
        val merged = mutableListOf<Int>()
        var score = 0
        var i = 0
        while (i < nonZero.size) {
            val current = nonZero[i]
            val next = nonZero.getOrNull(i + 1)
            if (next != null && next == current) {
                val mergedValue = current * 2
                merged.add(mergedValue)
                score += mergedValue
                i += 2
            } else {
                merged.add(current)
                i += 1
            }
        }
        val resultLine = IntArray(line.size) { index -> merged.getOrElse(index) { 0 } }
        val moved = !line.contentEquals(resultLine)
        return Triple(resultLine, score, moved)
    }

    private fun boardToGrid(board: Board): Array<IntArray> =
        Array(board.size) { row ->
            IntArray(board.size) { col -> board.tileAt(row, col)?.value ?: 0 }
        }

    private fun gridToBoard(grid: Array<IntArray>, size: Int): Board {
        val tiles = mutableListOf<Tile>()
        for (row in 0 until size) {
            for (col in 0 until size) {
                val value = grid[row][col]
                if (value != 0) {
                    tiles.add(Tile(id = nextTileId++, value = value, row = row, col = col))
                }
            }
        }
        return Board(size = size, tiles = tiles)
    }

    /** Rotates the grid so "move left" logic can be reused for every direction. */
    private fun rotateForDirection(grid: Array<IntArray>, direction: Direction): Array<IntArray> =
        when (direction) {
            Direction.LEFT -> grid
            Direction.RIGHT -> grid.map { it.reversedArray() }.toTypedArray()
            Direction.UP -> transpose(grid)
            Direction.DOWN -> transpose(grid).map { it.reversedArray() }.toTypedArray()
        }

    private fun unrotateForDirection(grid: Array<IntArray>, direction: Direction): Array<IntArray> =
        when (direction) {
            Direction.LEFT -> grid
            Direction.RIGHT -> grid.map { it.reversedArray() }.toTypedArray()
            Direction.UP -> transpose(grid)
            Direction.DOWN -> transpose(grid.map { it.reversedArray() }.toTypedArray())
        }

    private fun transpose(grid: Array<IntArray>): Array<IntArray> {
        val size = grid.size
        return Array(size) { row -> IntArray(size) { col -> grid[col][row] } }
    }

    private fun spawnRandomTile(board: Board): Board {
        val emptyCells = buildList {
            for (row in 0 until board.size) {
                for (col in 0 until board.size) {
                    if (board.tileAt(row, col) == null) add(row to col)
                }
            }
        }
        if (emptyCells.isEmpty()) return board

        val (row, col) = emptyCells.random(random)
        val value = if (random.nextDouble() < 0.9) 2 else 4
        val newTile = Tile(id = nextTileId++, value = value, row = row, col = col, isNew = true)
        return board.copy(tiles = board.tiles + newTile)
    }

    private fun isGameOver(board: Board): Boolean {
        val size = board.size
        if (board.tiles.size < size * size) return false

        for (row in 0 until size) {
            for (col in 0 until size) {
                val value = board.tileAt(row, col)?.value ?: return false
                val rightValue = board.tileAt(row, col + 1)?.value
                val downValue = board.tileAt(row + 1, col)?.value
                if (rightValue == value || downValue == value) return false
            }
        }
        return true
    }

    companion object {
        private const val WIN_VALUE = 2048
    }
}