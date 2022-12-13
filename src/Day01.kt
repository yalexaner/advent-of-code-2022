@file:JvmName("Day1Kt")

import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var maxCalories = 0
        var currentElfCalories = 0

        for (line in input) {
            if (line.isEmpty()) {
                maxCalories = maxOf(maxCalories, currentElfCalories)
                currentElfCalories = 0
                continue
            }

            currentElfCalories += line.toInt()
        }

        if (currentElfCalories != 0) {
            maxCalories = maxOf(maxCalories, currentElfCalories)
        }

        return maxCalories
    }

    fun part2(input: List<String>): Int {
        val topElvesCalories = Array(3) { 0 }
        var currentElfCalories = 0

        for (line in input) {
            if (line.isEmpty()) {
                topElvesCalories.addOrReplaceIfPossible(currentElfCalories)
                currentElfCalories = 0
                continue
            }

            currentElfCalories += line.toInt()
        }

        if (currentElfCalories != 0) {
            topElvesCalories.addOrReplaceIfPossible(currentElfCalories)
        }

        return topElvesCalories.sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun Array<Int>.addOrReplaceIfPossible(num: Int) {
    var currentMin = num
    var currentIdx = 0
    while (currentIdx < size) {
        if (get(currentIdx) < currentMin) {
            val tmp = this[currentIdx]
            this[currentIdx] = currentMin
            currentMin = tmp
        }
        currentIdx++
    }
}