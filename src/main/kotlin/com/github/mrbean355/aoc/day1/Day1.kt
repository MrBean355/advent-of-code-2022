package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.Puzzle

class Day1(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return countCalories(topElves = 1)
    }

    override fun part2(): Number {
        return countCalories(topElves = 3)
    }

    private fun countCalories(topElves: Int): Int {
        val calories = mutableListOf<Int>()
        var current = 0

        input.forEach { line ->
            if (line.isBlank()) {
                calories += current
                current = 0
            } else {
                current += line.toInt()
            }
        }

        calories += current
        return calories.sortedDescending().take(topElves).sum()
    }
}