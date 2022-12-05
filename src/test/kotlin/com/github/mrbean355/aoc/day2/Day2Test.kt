package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.PuzzleTest

class Day2Test : PuzzleTest(Day2::class) {

    override val part1TestCases = mapOf(
        "day2/example.txt" to 15,
        "day2/puzzle.txt" to 12679,
    )

    override val part2TestCases: Map<String, Any>
        get() = emptyMap()

}