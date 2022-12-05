package com.github.mrbean355.aoc.day3

import com.github.mrbean355.aoc.base.Puzzle

class Day3(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.sumOf { line ->
            val size = line.length / 2
            val first = line.take(size).toCharArray()
            val second = line.takeLast(size).toCharArray().toSet()

            first.intersect(second).single().priority()
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun Char.priority(): Int {
        return if (this <= 'Z') {
            (code - 64) + 26
        } else {
            code - 96
        }
    }
}