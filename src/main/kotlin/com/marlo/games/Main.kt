package com.marlo.games

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import mu.KotlinLogging
import kotlin.system.exitProcess

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
  require(mandatory.length == 1) { "mandatory character must be a single character" }
  logger.debug("dictionary: $dictionary")
  logger.debug("size: $size")
  logger.debug("mandatory: $mandatory")
  logger.debug("letters: $letters")

  // prepare game bean and solve puzzle
  try {
    val game = Game(dictionary, size, mandatory.first(), letters)
    solve(game).forEach { println(it) }
    exitProcess(0)
  } catch (t: Throwable) {
    logger.error("${t.message}")
    exitProcess(1)
  }
}
