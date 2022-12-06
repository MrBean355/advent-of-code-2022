package com.github.mrbean355.aoc.day5

import com.github.mrbean355.aoc.base.Puzzle

class Day5(private val input: List<String>) : Puzzle {

    override fun part1(): String {
        val stacks = SupplyStacks.from(input)

        input.forEach { line ->
            if (line.startsWith("move")) {
                stacks.moveIndividually(line)
            }
        }

        return stacks.topCrates()
    }

    override fun part2(): String {
        val stacks = SupplyStacks.from(input)

        input.forEach { line ->
            if (line.startsWith("move")) {
                stacks.moveSimultaneously(line)
            }
        }

        return stacks.topCrates()
    }
}