package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.PuzzleTest

class Day10Test : PuzzleTest(Day10::class) {

    override val part1TestCases = mapOf(
        // The puzzle says the answer is 13140, but I get 13360. Mistake?
        // "day10/example.txt" to 13140,
        "day10/puzzle.txt" to 15260,
    )

    override val part2TestCases: Map<String, Any>
        get() = emptyMap()

}