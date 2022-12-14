@file:JvmName("Day1Kt")

import kotlin.math.max

fun main() {
    runTests()

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun runTests() {
    val testInput = readInput("Day01_test")
    val part1Result = part1(testInput)
    check(part1Result == 24000) { "part1 is $part1Result, but expected 24000" }
    val part2Result = part2(testInput)
    check(part2Result == 45000) { "part2 is $part2Result, but exprected 45000"}
}

private fun part1(input: List<String>): Int {
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

private fun part2(input: List<String>): Int {
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

private fun Array<Int>.addOrReplaceIfPossible(num: Int) {
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