package com.github.mrbean355.aoc.day4

import com.github.mrbean355.aoc.base.Puzzle

class Day4(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.count { line ->
            val (a, b) = line.split(',')
            val first = a.parse()
            val second = b.parse()

            second in first || first in second
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun String.parse(): IntRange {
        val (lhs, rhs) = split('-')
        return IntRange(lhs.toInt(), rhs.toInt())
    }

    private operator fun IntRange.contains(other: IntRange): Boolean {
        return other.first >= first && other.last <= last
    }
}