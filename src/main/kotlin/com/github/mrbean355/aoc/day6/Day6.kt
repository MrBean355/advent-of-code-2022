package com.github.mrbean355.aoc.day6

import com.github.mrbean355.aoc.base.Puzzle

private const val UNIQUE_CHARS = 4

class Day6(input: List<String>) : Puzzle {
    private val signal = input.single()

    override fun part1(): Number {
        signal.windowed(UNIQUE_CHARS).forEachIndexed { index, window ->
            if (window.toSet().size == UNIQUE_CHARS) {
                return index + UNIQUE_CHARS
            }
        }
        error("Failed to find solution")
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}