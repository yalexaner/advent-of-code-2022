@file:JvmName("Day2Kt")

import kotlin.math.max


fun main() {
    runTests()

    val input = readInput("Day02")
    println(part1(input))
}

private fun runTests() {
    val testInput = readInput("Day02_test")
    val part1Result = part1(testInput)
    check(part1Result == 15) { "part1 is $part1Result, but expected 15" }
}

private fun part1(input: List<String>): Int {
    var score = 0
    
    for (position in input) {
        val outcome = computeOutcome(position) ?: return -1
        score += computeScore(outcome, position) ?: return -1
    }

    return score
}

/**
 * Enemy's  hand: A for Rock, B for Paper, and C for Scissors
 * Player's hand: X for Rock, Y for Paper, and Z for Scissors
 */
private fun computeOutcome(position: String): Outcome? {
    val winPositions = listOf("A Y", "B Z", "C X")
    val losePositions = listOf("A Z", "B X", "C Y")
    val drawPositions = listOf("A X", "B Y", "C Z")

    return when (position) {
        in winPositions -> Outcome.WIN
        in losePositions -> Outcome.LOSE
        in drawPositions -> Outcome.DRAW
        else -> null
    }
}

private fun computeScore(outcome: Outcome, position: String): Int? {
    var score = 0

    score += when (outcome) {
        Outcome.WIN -> 6
        Outcome.DRAW -> 3
        Outcome.LOSE -> 0
    }

    val playersHand = position.split(" ").last()
    score += when (playersHand) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> return null
    }

    return score
}

private enum class Outcome {
    WIN, LOSE, DRAW
}
