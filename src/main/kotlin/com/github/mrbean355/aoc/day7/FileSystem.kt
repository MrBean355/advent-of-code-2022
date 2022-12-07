package com.github.mrbean355.aoc.day7

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