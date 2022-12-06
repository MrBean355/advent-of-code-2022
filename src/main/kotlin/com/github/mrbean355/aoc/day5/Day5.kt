package com.github.mrbean355.aoc.day5

import com.github.mrbean355.aoc.base.Puzzle

class Day5(private val input: List<String>) : Puzzle {

    override fun part1(): String {
        val stacks = mutableMapOf<Int, MutableList<Char>>()
        var movingCrates = false

        input.forEach { line ->
            if (!movingCrates) {
                if ('[' in line) {
                    line.chunked(4).map(String::trim).forEachIndexed { index, crate ->
                        val stack = stacks.getOrPut(index, ::mutableListOf)
                        if (crate.isNotEmpty()) {
                            stack.add(0, crate[1])
                        }
                    }
                } else {
                    movingCrates = true
                }
            } else if (line.isNotBlank()) {
                val args = line.split(' ')
                val count = args[1].toInt()
                val from = args[3].toInt() - 1
                val to = args[5].toInt() - 1

                repeat(count) {
                    stacks.getValue(to).add(stacks.getValue(from).removeLast())
                }
            }
        }

        return stacks.values.joinToString(separator = "") {
            it.last().toString()
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}