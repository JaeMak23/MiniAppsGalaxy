package com.jaemak23.miniappsgalaxy.feature.games.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun TicTacToeGame(onExit: () -> Unit) {
    // Game State
    var board by remember { mutableStateOf(List(9) { "" }) }
    var isXTurn by remember { mutableStateOf(true) } // Player is always X
    var winner by remember { mutableStateOf<String?>(null) }

    val winningCombinations = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    fun checkWinner(currentBoard: List<String>): String? {
        for (combination in winningCombinations) {
            val (a, b, c) = combination
            if (currentBoard[a].isNotEmpty() &&
                currentBoard[a] == currentBoard[b] &&
                currentBoard[a] == currentBoard[c]
            ) {
                return currentBoard[a]
            }
        }
        return if (currentBoard.all { it.isNotEmpty() }) "Draw" else null
    }

    // Handles the player's move
    fun onCellClick(index: Int) {
        // Only allow clicking if the cell is empty, there is no winner, AND it is the player's turn
        if (board[index].isEmpty() && winner == null && isXTurn) {
            val newBoard = board.toMutableList()
            newBoard[index] = "X"
            board = newBoard
            winner = checkWinner(newBoard)
            isXTurn = false // Hand over to AI
        }
    }

    fun resetGame() {
        board = List(9) { "" }
        isXTurn = true
        winner = null
    }

    // AI Bot Logic triggered by a LaunchedEffect
    LaunchedEffect(isXTurn, winner) {
        // If it's not X's turn and the game isn't over yet
        if (!isXTurn && winner == null) {
            delay(500.milliseconds) // Add a slight pause for realism

            val availableIndices = board.indices.filter { board[it].isEmpty() }
            if (availableIndices.isNotEmpty()) {
                val botMove = availableIndices.random() // Pick a random empty spot

                val newBoard = board.toMutableList()
                newBoard[botMove] = "O"
                board = newBoard
                winner = checkWinner(newBoard)
                isXTurn = true // Hand back to player
            }
        }
    }

    // UI Layout
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onExit) {
            Text("Exit Tic-tac-toe")
        }
        Text(
            text = "Tic-Tac-Toe vs Bot",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Column(
            modifier = Modifier
                .background(Color(0xFF333333), RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            for (i in 0 until 3) {
                Row {
                    for (j in 0 until 3) {
                        val index = i * 3 + j
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(4.dp)
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .clickable { onCellClick(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = board[index],
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (board[index] == "X") Color(0xFF2196F3) else Color(
                                    0xFFF44336
                                )
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (winner != null) {
            Text(
                text = if (winner == "Draw") "It's a Draw!" else if (winner == "X") "You Win!" else "Bot Wins!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
        } else {
            Text(
                text = if (isXTurn) "Your Turn (X)" else "Bot is thinking...",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { resetGame() }) {
            Text(text = "Restart Game", fontSize = 18.sp)
        }
    }
}