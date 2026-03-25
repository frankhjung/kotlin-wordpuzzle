package com.marlo.games

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WordPuzzleTest {

  companion object {
    const val LETTERS = "adevcrsoi"
  }

  @Test
  fun isValidWord() = assertTrue(isValid(LETTERS, "voiced"))

  @Test
  fun isInvalidWord() = assertFalse(isValid(LETTERS, "voixed"))

  @Test
  fun tooManyOs() = assertFalse(isValid(LETTERS, "vooiced"))

  @Test
  fun tooLarge() = assertFalse(isValid(LETTERS, LETTERS + "moreletters"))

  @Test
  fun testGameValidation() {
    // Valid game
    Game("dictionary/british", 4, 'c', LETTERS)

    // Invalid size
    assertFailsWith<IllegalArgumentException> {
      Game("dictionary/british", 0, 'c', LETTERS)
    }

    // Invalid letters length
    assertFailsWith<IllegalArgumentException> {
      Game("dictionary/british", 4, 'c', "abc")
    }

    // Mandatory char not in letters
    assertFailsWith<IllegalArgumentException> {
      Game("dictionary/british", 4, 'z', LETTERS)
    }

    // Dictionary does not exist
    assertFailsWith<IllegalArgumentException> {
      Game("nonexistent", 4, 'c', LETTERS)
    }
  }

  @Test
  fun testSolveSorting() {
    // Create a temporary dictionary
    val tempFile = File.createTempFile("test_dict", ".txt")
    tempFile.writeText("voiced\nvoice\ncode\ncoed\ncod\n")

    try {
      val game = Game(tempFile.absolutePath, 3, 'c', LETTERS)
      val results = solve(game)

      // Expected: voiced (6), voice (5), code (4), coed (4), cod (3)
      // Note: "code" and "coed" are same length, so they should be alphabetical
      assertEquals(listOf("voiced", "voice", "code", "coed", "cod"), results)
    } finally {
      tempFile.delete()
    }
  }
}
