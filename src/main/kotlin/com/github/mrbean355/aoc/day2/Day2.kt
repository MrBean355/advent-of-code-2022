package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.Puzzle

class Day2(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.sumOf { line ->
            val (them, me) = line.parse()
            calculatePoints(them.decodeMove(), me.decodeMove())
        }
    }

    override fun part2(): Number {
        return input.sumOf { line ->
            val (them, outcome) = line.parse()
            val me = chooseMove(them.decodeMove(), outcome.decodeOutcome())
            calculatePoints(them.decodeMove(), me)
        }
    }

    private fun String.parse(): Pair<Char, Char> {
        val (a, b) = split(' ').take(2).map(String::single)
        return a to b
    }

    private fun Char.decodeMove(): Move {
        return when (this) {
            'A', 'X' -> Move.Rock
            'B', 'Y' -> Move.Paper
            'C', 'Z' -> Move.Scissors
            else -> error("Unexpected move: $this")
        }
    }

    private fun Char.decodeOutcome(): Outcome {
        return when (this) {
            'X' -> Outcome.Lose
            'Y' -> Outcome.Draw
            'Z' -> Outcome.Win
            else -> error("Unexpected move: $this")
        }
    }

    private fun chooseMove(them: Move, outcome: Outcome): Move {
        return when (outcome) {
            Outcome.Draw -> them
            Outcome.Lose -> them.getLosingMove()
            Outcome.Win -> them.getWinningMove()
        }
    }
}