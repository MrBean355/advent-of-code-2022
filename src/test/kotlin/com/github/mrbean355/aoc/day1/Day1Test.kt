package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.PuzzleTest

class Day1Test : PuzzleTest(Day1::class) {

    override val part1TestCases = mapOf(
        "day1/example.txt" to 24000,
        "day1/puzzle.txt" to 71924,
    )

    override val part2TestCases = mapOf(
        "day1/example.txt" to 45000,
        "day1/puzzle.txt" to 210406,
    )

}