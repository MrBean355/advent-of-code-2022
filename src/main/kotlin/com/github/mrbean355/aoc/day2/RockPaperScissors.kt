package com.github.mrbean355.aoc.day2

enum class Move {
    Rock, Paper, Scissors
}

private val winners = mapOf(
    Move.Rock to Move.Scissors,
    Move.Scissors to Move.Paper,
    Move.Paper to Move.Rock,
)

enum class Outcome {
    Win, Lose, Draw
}

fun calculatePoints(them: Move, me: Move): Int {
    val movePoints = when (me) {
        Move.Rock -> 1
        Move.Paper -> 2
        Move.Scissors -> 3
    }
    val outcomePoints = when (them) {
        me -> 3
        winners[me] -> 6
        else -> 0
    }

    return movePoints + outcomePoints
}

/** @return the move that beats [this]. */
fun Move.getWinningMove(): Move {
    return winners.entries.first { it.value == this }.key
}

/** @return the move that is beaten by [this]. */
fun Move.getLosingMove(): Move {
    return winners.getValue(this)
}