package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.Puzzle

class Day1(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        var highest = 0
        var current = 0

        input.forEach { line ->
            if (line.isBlank()) {
                highest = maxOf(highest, current)
                current = 0
            } else {
                current += line.toInt()
            }
        }

        return highest
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}