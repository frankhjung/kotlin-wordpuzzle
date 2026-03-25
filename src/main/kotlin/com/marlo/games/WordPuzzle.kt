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
 * Check if a dictionary word is valid.
 *
 * @param letters the letters to validate against
 * @param word the dictionary word to check
 * @return true if word is valid
 */
fun isValid(letters: String, word: String): Boolean {
  if (word.length > letters.length) return false
  val counts = IntArray(26)
  for (c in letters) {
    if (c !in 'a'..'z') return false
    counts[c - 'a']++
  }
  for (c in word) {
    if (c !in 'a'..'z') return false
    if (--counts[c - 'a'] < 0) return false
  }
  return true
}

/**
 * Solve Word puzzle by filtering dictionary for only valid words.
 *
 * @param game
 * @return list of words sorted by length (descending) then alphabetically
 */
fun solve(game: Game): List<String> =
  File(game.dictionary).bufferedReader().use { reader ->
    reader
      .lineSequence()
      .filter { it.length in game.size..9 }
      .filter { it.contains(game.mandatory) }
      .filter { isValid(game.letters, it) }
      .toMutableList()
      .apply { sortWith(compareByDescending<String> { it.length }.thenBy { it }) }
  }
