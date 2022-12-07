package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.Puzzle

private const val CMD = '$'
private const val CMD_LIST = "$CMD ls"
private const val CMD_CD = "$CMD cd"
private const val DIR_PREFIX = "dir "
private const val PARENT_DIR = ".."

class Day7(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val root = Directory("/", parent = null)
        var current: Directory = root
        var index = 1

        while (true) {
            val cmd = input[index++]

            if (cmd.startsWith(CMD_LIST)) {
                while (true) {
                    val output = input[index++]
                    if (output.startsWith(CMD)) {
                        --index
                        break
                    } else {
                        current.children += if (output.startsWith(DIR_PREFIX)) {
                            Directory(output.substringAfter(' '), parent = current)
                        } else {
                            val (size, name) = output.split(' ')
                            File(name, size.toLong(), parent = current)
                        }
                    }
                    if (index == input.size) {
                        break
                    }
                }
            } else if (cmd.startsWith(CMD_CD)) {
                val arg = cmd.substringAfterLast(' ')
                current = if (arg == PARENT_DIR) {
                    current.parent!!
                } else {
                    current.children.filterIsInstance<Directory>()
                        .first { it.name == arg }
                }
            } else {
                error("Unknown command: $cmd")
            }

            if (index == input.size) {
                break
            }
        }

        return root.restrictedSize()
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}

fun Directory.restrictedSize(): Long {
    val mySize = if (size <= 100_000) size else 0
    return mySize + children.filterIsInstance<Directory>()
        .sumOf(Directory::restrictedSize)
}