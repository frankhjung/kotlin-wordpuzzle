package com.marlo.games

import kotlin.test.Test
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
}
