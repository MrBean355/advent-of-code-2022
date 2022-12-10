package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.Puzzle

class Day9(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return simulateRope(knots = 2)
    }

    override fun part2(): Number {
        return simulateRope(knots = 10)
    }

    private fun simulateRope(knots: Int): Int {
        val head = assembleRope(knots)

        input.forEach { line ->
            val (dir, amount) = line.split(' ')

            repeat(amount.toInt()) {
                head.move(dir)
            }
        }

        var current = head

        while (true) {
            current = current.next ?: return current.pointsVisited()
        }
    }

    private fun assembleRope(knots: Int): Knot {
        var tail = Knot(trackVisitedPoints = true)

        repeat(knots - 1) {
            val n = Knot(next = tail)
            tail = n
        }

        return tail
    }
}