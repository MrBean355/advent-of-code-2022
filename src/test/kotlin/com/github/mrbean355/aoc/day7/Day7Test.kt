package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.PuzzleTest

class Day7Test : PuzzleTest(Day7::class) {

    override val part1TestCases = mapOf(
        "day7/example.txt" to 95_437L,
        "day7/puzzle.txt" to 1_749_646L,
    )

    override val part2TestCases: Map<String, Any>
        get() = emptyMap()

}