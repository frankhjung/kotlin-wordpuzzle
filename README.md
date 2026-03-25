# Kotlin Solution to 9 Letter Word Puzzle

Explore Kotlin to solve [9 Letter word
Puzzle](https://nineletterword.tompaton.com/adevcrsoi/).

See also the other language solutions:

* [Clojure](https://github.com/frankhjung/clojure-wordpuzzle)
* [Haskell](https://github.com/frankhjung/haskell-wordpuzzle)
* [Java](https://github.com/frankhjung/java-wordpuzzle)
* [Kotlin](https://github.com/frankhjung/kotlin-wordpuzzle)
* [Go](https://github.com/frankhjung/go-wordpuzzle)
* [Python](https://github.com/frankhjung/python-wordpuzzle)

## Quick Start

To quickly build, test, and run an example puzzle solution:

```bash
# Build and run tests
./gradlew build

# Run an example puzzle
./gradlew run --args="-m c -l adevcrsoi"
```

## Format

To check if the code is formatted correctly (this will fail the build if there
are formatting violations):

```bash
./gradlew spotlessCheck
```

To automatically fix formatting issues and apply the correct format to all
files:

```bash
./gradlew spotlessApply
```

## Build

This project requires **Java 21** or later.

To build this project with Gradle [Kotlin
DLS](https://docs.gradle.org/current/userguide/kotlin_dsl.html), run

```bash
./gradlew build
```

This will run tests and build the Kotlin package.

To build a fat JAR I am using
[Gradle Shadow](https://imperceptiblethoughts.com/shadow/)

```bash
./gradlew shadowJar
```

## Test

To run tests, use

```bash
./gradlew test
```

## Run

Two ways to run puzzle solution:

```bash
./gradlew run --args="-m c -l adevcrsoi"

java -jar build/libs/wordpuzzle-2.0.0-all.jar -m c -l adevcrsoi
```

## Help

Print program help message using one of:

```bash
./gradlew run --args='-h'
java -jar build/libs/wordpuzzle-2.0.0-all.jar -h
```

This will print the help message:

```text
Usage: wordpuzzle options_list
Options:
    --dictionary, -d [dictionary/british] -> dictionary to use in word search { String }
    --size, -s [4] -> minimum word size { Int }
    --mandatory, -m -> mandatory character (always required) { String }
    --letters, -l -> letters to create words from (always required) { String }
    --help, -h -> Usage info
```

## References

* [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
* [Kotlin API Documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/)
