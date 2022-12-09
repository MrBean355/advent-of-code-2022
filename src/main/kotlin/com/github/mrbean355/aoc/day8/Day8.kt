package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.Puzzle

class Day8(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return Forest.from(input).countTreesVisibleFromOutside()
    }

    override fun part2(): Number {
        return Forest.from(input).calculateBestScenicScore()
    }
}