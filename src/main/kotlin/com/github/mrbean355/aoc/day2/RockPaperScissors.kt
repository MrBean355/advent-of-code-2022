package com.github.mrbean355.aoc.day2

const val Rock = 'A'
const val Paper = 'B'
const val Scissors = 'C'

private val winners = mapOf(
    Rock to Scissors,
    Scissors to Paper,
    Paper to Rock,
)

private val points = mapOf(
    Rock to 1,
    Paper to 2,
    Scissors to 3,
)

private fun Char.beats(other: Char): Boolean {
    return winners[this] == other
}

fun battle(them: Char, me: Char): Int {
    return when {
        them == me -> 3
        me.beats(them) -> 6
        else -> 0
    } + points.getValue(me)
}