package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.Puzzle

class Day2(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.sumOf { line ->
            val (them, me) = line.split(' ').take(2).map(String::single)
            battle(them, me.decode())
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun Char.decode(): Char {
        return when (this) {
            'X' -> Rock
            'Y' -> Paper
            'Z' -> Scissors
            else -> error("Unexpected move: $this")
        }
    }
}