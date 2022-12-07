package com.github.mrbean355.aoc.day7

private const val CMD = '$'
private const val CMD_LIST = "$CMD ls"
private const val CMD_CD = "$CMD cd"
private const val DIR_PREFIX = "dir "
private const val PARENT_DIR = ".."

fun loadFileSystem(
    input: List<String>
): Directory {
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

    return root
}

sealed interface Node {
    val name: String
    val size: Long
    val parent: Directory?
}

class Directory(
    override val name: String,
    override val parent: Directory?,
) : Node {

    val children = mutableListOf<Node>()

    override val size: Long
        get() = children.sumOf(Node::size)

    override fun toString(): String {
        return "- $name (dir)"
    }
}

class File(
    override val name: String,
    override val size: Long,
    override val parent: Directory?,
) : Node {

    override fun toString(): String {
        return "- $name (file, size=$size)"
    }
}

fun Node.print(level: Int = 0) {
    val prefix = buildString {
        repeat(level) { append("  ") }
    }
    println(prefix + toString())
    if (this is Directory) {
        children.forEach {
            it.print(level = level + 1)
        }
    }
}