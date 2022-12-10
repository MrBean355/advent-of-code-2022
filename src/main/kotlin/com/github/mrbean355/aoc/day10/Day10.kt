package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.Puzzle

private const val CRT_WIDTH = 40
private const val CRT_HEIGHT = 6

class Day10(private val input: List<String>) : Puzzle {

    private var register = 1
    private var cycles = 0
    private var totalSignalStrength = 0
    private val drawnPixels = mutableListOf<Int>()

    override fun part1(): Number {
        executeCommands()
        return totalSignalStrength
    }

    override fun part2(): String {
        executeCommands()
        return renderImage()
    }

    private fun executeCommands() {
        input.forEach { command ->
            when {
                command == "noop" -> cycle()
                command.startsWith("addx") -> {
                    cycle()
                    cycle()
                    register += command.substringAfter(' ').toInt()
                }

                else -> error("Unexpected command: $command")
            }
        }
    }

    private fun cycle() {
        if (cycles % CRT_WIDTH in (register - 1..register + 1)) {
            // Draw the current pixel if it lines up with the sprite's position.
            drawnPixels += cycles
        }

        ++cycles

        if (cycles == 20 || (cycles - 20) % 40 == 0) {
            totalSignalStrength += cycles * register
        }
    }

    private fun renderImage(): String {
        return buildString {
            repeat(CRT_WIDTH * CRT_HEIGHT) {
                if (it in drawnPixels) {
                    append('#')
                } else {
                    append(' ')
                }
            }
        }.chunked(CRT_WIDTH).joinToString(separator = "\n")
    }
}