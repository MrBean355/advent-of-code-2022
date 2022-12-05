package com.github.mrbean355.aoc.day4

import com.github.mrbean355.aoc.base.Puzzle

class Day4(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.count { line ->
            val (first, second) = line.parsePair()
            first.contains(second) || second.contains(first)
        }
    }

    override fun part2(): Number {
        return input.count { line ->
            val (first, second) = line.parsePair()
            first.overlaps(second) || second.overlaps(first)
        }
    }

    private fun String.parsePair(): Pair<IntRange, IntRange> {
        val (a, b) = split(',')
        return a.parseRange() to b.parseRange()
    }

    private fun String.parseRange(): IntRange {
        val (lhs, rhs) = split('-')
        return IntRange(lhs.toInt(), rhs.toInt())
    }

    private fun IntRange.contains(other: IntRange): Boolean {
        return other.first >= first && other.last <= last
    }

    private fun IntRange.overlaps(other: IntRange): Boolean {
        return other.first <= last && other.last >= first
    }
}