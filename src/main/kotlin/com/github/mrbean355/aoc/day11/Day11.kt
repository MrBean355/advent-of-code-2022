package com.github.mrbean355.aoc.day11

import com.github.mrbean355.aoc.base.Puzzle

class Day11(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val monkeys = input.filter(String::isNotBlank)
            .chunked(6)
            .map { Monkey.from(it) }

        repeat(20) {
            monkeys.forEach { monkey ->
                monkey.inspectItems { target, item ->
                    monkeys[target].catchItem(item)
                }
            }
        }

        return monkeys.map(Monkey::inspections)
            .sortedDescending()
            .take(2)
            .fold(1) { acc, value -> acc * value }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}