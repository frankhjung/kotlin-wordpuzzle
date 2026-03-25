package com.marlo.games

import java.io.File

data class Game(val dictionary: String, val size: Int, val mandatory: Char, val letters: String) {

  /** Validate all parameters. */
  init {
    require(File(dictionary).exists()) { "cannot open dictionary $dictionary" }
    require(size in 1..9) { "invalid size, must be in range 1 to 9" }
    require(letters.length == 9 && isAllLowerCaseLetters(letters)) { "require 9 letters" }
    require(letters.contains(mandatory)) { "mandatory not in letters" }
  }
}

/**
 * Check if letters are all lowercase.
 * @param letters the letters to check
 * @return true if string contains only lowercase letters
 */
fun isAllLowerCaseLetters(letters: String): Boolean = letters.all { it in 'a'..'z' }

/**
 * Check if a dictionary word can be formed from the provided letters.
 *
 * Validates that the word does not exceed the available letters, contains only
 * lowercase characters, and that the frequency of each character in the word
 * does not exceed its frequency in the provided letters pool.
 *
 * @param letters the pool of available letters
 * @param word the dictionary word to check
 * @return true if the word can be formed from the letters
 */
fun isValid(letters: String, word: String): Boolean {
  if (word.length > letters.length) return false
  if (!isAllLowerCaseLetters(letters) || !isAllLowerCaseLetters(word)) return false

  val letterCounts = letters.groupingBy { it }.eachCount()
  return word.groupingBy { it }.eachCount().all { (char, count) ->
    count <= letterCounts.getOrDefault(char, 0)
  }
}

/**
 * Solve Word puzzle by filtering dictionary for only valid words.
 *
 * @param game the game configuration
 * @return list of words sorted by length (descending) then alphabetically
 */
fun solve(game: Game): List<String> =
  File(game.dictionary).bufferedReader().use { reader ->
    reader
      .lineSequence() // Read lazily line-by-line to minimize memory footprint
      .filter { it.length in game.size..9 } // Rule 1: check minimum size and maximum grid size
      .filter { it.contains(game.mandatory) } // Rule 2: must contain the central mandatory letter
      .filter { isValid(game.letters, it) } // Rule 3: can only be formed using available letters
      .toMutableList() // Materialize results into a list for sorting
      .apply { sortWith(compareByDescending<String> { it.length }.thenBy { it }) } // Sort longest words first, then A-Z
  }
