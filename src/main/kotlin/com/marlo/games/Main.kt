package com.marlo.games

import kotlin.system.exitProcess
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import mu.KotlinLogging

/** Logger for project. */
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
  // define parser
  val parser = ArgParser("wordpuzzle")
  val dictionary by parser
      .option(ArgType.String, shortName = "d", description = "dictionary to use in word search")
      .default("dictionary/british")
  val size by parser
      .option(ArgType.Int, shortName = "s", description = "minimum word size")
      .default(4)
  val mandatory by parser
      .option(ArgType.String, shortName = "m", description = "mandatory character")
      .required()
  val letters by parser
      .option(ArgType.String, shortName = "l", description = "letters to create words from")
      .required()

  // get username from commandline argument
  parser.parse(args)

  logger.debug("dictionary: $dictionary")
  logger.debug("size: $size")
  logger.debug("mandatory: $mandatory")
  logger.debug("letters: $letters")

  // prepare game bean
  try {
    val game = Game(dictionary, size, mandatory.first(), letters)
    wordPuzzle(game).forEach { println(it) }
    exitProcess(0)
  } catch (t: Throwable) {
    logger.error("${t.message}")
    exitProcess(1)
  }
}
