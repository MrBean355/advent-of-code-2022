package com.github.mrbean355.aoc.day5

class SupplyStacks {
    private val stacks = mutableMapOf<Int, MutableList<Char>>()

    fun moveIndividually(line: String) {
        val args = line.split(' ')
        val count = args[1].toInt()
        val from = args[3].toInt() - 1
        val to = args[5].toInt() - 1

        repeat(count) {
            get(to).add(get(from).removeLast())
        }
    }

    fun moveSimultaneously(line: String) {
        val args = line.split(' ')
        val count = args[1].toInt()
        val from = args[3].toInt() - 1
        val to = args[5].toInt() - 1

        get(to).addAll(get(from).takeLast(count))
        repeat(count) {
            get(from).removeLast()
        }
    }

    fun topCrates(): String {
        return stacks.values.joinToString(separator = "") {
            it.last().toString()
        }
    }

    private fun get(index: Int): MutableList<Char> {
        return stacks.getValue(index)
    }

    companion object {

        fun from(input: List<String>): SupplyStacks {
            val instance = SupplyStacks()
            input.forEach { line ->
                if ('[' in line) {
                    line.chunked(4).map(String::trim).forEachIndexed { index, crate ->
                        val stack = instance.stacks.getOrPut(index, ::mutableListOf)
                        if (crate.isNotEmpty()) {
                            stack.add(0, crate[1])
                        }
                    }
                } else {
                    return@forEach
                }
            }
            return instance
        }
    }
}