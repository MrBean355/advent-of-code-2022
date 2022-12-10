package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.Puzzle

class Day10(private val input: List<String>) : Puzzle {

    private var x = 1
    private var cycles = 0
    private var signalStrength = 0

    override fun part1(): Number {
        input.forEach { instruction ->
            when {
                instruction == "noop" -> cycle()
                instruction.startsWith("addx") -> {
                    cycle()
                    x += instruction.substringAfter(' ').toInt()
                    cycle()
                }

                else -> error("Unexpected instruction: $instruction")
            }
        }

        return signalStrength
    }

    private fun cycle() {
        ++cycles

        if (cycles == 20 || (cycles - 20) % 40 == 0) {
            signalStrength += cycles * x
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}