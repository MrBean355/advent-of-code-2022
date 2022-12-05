package com.github.mrbean355.aoc.day3

import com.github.mrbean355.aoc.base.Puzzle

class Day3(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.sumOf { line ->
            val size = line.length / 2
            val first = line.take(size).toList()
            val second = line.takeLast(size).toSet()

            first.intersect(second).single().priority()
        }
    }

    override fun part2(): Number {
        return input.windowed(size = 3, step = 3).sumOf { elves ->
            val first = elves[0].toList()
            val second = elves[1].toSet()
            val third = elves[2].toSet()

            first.intersect(second).intersect(third).single().priority()
        }
    }

    private fun Char.priority(): Int {
        return if (this <= 'Z') {
            (code - 64) + 26
        } else {
            code - 96
        }
    }
}