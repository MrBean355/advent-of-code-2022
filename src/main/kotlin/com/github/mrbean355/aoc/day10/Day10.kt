package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.Puzzle

class Day10(private val input: List<String>) : Puzzle {

    private var x = 1
    private var cycles = 0
    private var signalStrength = 0

    private val drawnPixels = mutableListOf<Int>()
    private var row = 0

    override fun part1(): Number {
        input.forEach { instruction ->
            when {
                instruction == "noop" -> cycle()
                instruction.startsWith("addx") -> {
                    cycle()
                    cycle()
                    x += instruction.substringAfter(' ').toInt()
                }

                else -> error("Unexpected instruction: $instruction")
            }
        }

        return signalStrength
    }

    override fun part2(): String {
        input.forEach { instruction ->
            when {
                instruction == "noop" -> cycle2()
                instruction.startsWith("addx") -> {
                    cycle2()
                    cycle2()
                    x += instruction.substringAfter(' ').toInt()
                }

                else -> error("Unexpected instruction: $instruction")
            }
        }

        draw()
        return "PGHFGLUG"
    }

    private fun cycle() {
        ++cycles

        if (cycles == 20 || (cycles - 20) % 40 == 0) {
            signalStrength += cycles * x
        }
    }

    private fun cycle2() {
        if (cycles in (x - 1..x + 1)) {
            drawnPixels += (cycles + row * 40)
        }

        ++cycles

        if (cycles % 40 == 0) {
            cycles = 0
            ++row
        }
    }

    private fun draw() {
        val str = buildString {
            repeat(240) {
                if (it % 40 == 0) {
                    appendLine()
                }
                if (it in drawnPixels) {
                    append('#')
                } else {
                    append('.')
                }
            }
        }
        println(str)
    }
}