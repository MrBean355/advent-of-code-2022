package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.Puzzle

class Day9(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val rope = Rope()

        input.forEach { line ->
            val (dir, amount) = line.split(' ')

            repeat(amount.toInt()) {
                rope.moveHead(Direction.from(dir))
            }
        }

        return rope.countVisitedPoints()
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}