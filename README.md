# Kotlin Solution to 9 Letter Word Puzzle

Explore Kotlin to solve [9 Letter word
Puzzle](https://nineletterword.tompaton.com/adevcrsoi/).

See also the other language solutions:

* [Haskell](https://gitlab.com/frankhjung1/haskell-wordpuzzle)
* [Java](https://gitlab.com/frankhjung1/java-wordpuzzle)
* [Kotlin](https://gitlab.com/frankhjung1/kotlin-wordpuzzle)
* [Go](https://gitlab.com/frankhjung1/go-wordpuzzle)
* [Python](https://gitlab.com/frankhjung1/python-wordpuzzle)

## Build

To build this project with Gradle [Kotlin
DLS](https://docs.gradle.org/current/userguide/kotlin_dsl.html), run

```bash
./gradle spotlessApply compileKotlin
```

This will format code and build the Kotlin package.

## Run

To build a fat JAR I am using [Gradle Shadow](https://imperceptiblethoughts.com/shadow/)

```bash
./gradlew run --args="-h"
kotlin -cp build/libs/games-0.0.1-all.jar com.marlo.games.MainKt -h
java -cp build/libs/games-0.0.1-all.jar com.marlo.games.MainKt -h
```

## Examples

### Help

Print program help message using one of:

```bash
./gradlew run --args="-h"
kotlin -cp build/libs/games-0.0.1-all.jar com.marlo.games.MainKt -h
java -cp build/libs/games-0.0.1-all.jar com.marlo.games.MainKt -h
```

This will print the help message:

For example:

```bash
$ java -cp build/libs/games-0.0.1-all.jar com.marlo.games.MainKt -h
Usage: wordpuzzle options_list
Options:
    --dictionary, -d [dictionary/british] -> dictionary to use in word search { String }
    --size, -s [4] -> minimum word size { Int }
    --mandatory, -m -> mandatory character (always required) { String }
    --letters, -l -> letters to create words from (always required) { String }
    --help, -h -> Usage info
```

### Run

Run puzzle solution over reference data:

```bash
./gradlew run --args="-m c -l adevcrsoi"
```

## References

* [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
* [Kotlin API Documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/)
* [WordPuzzle - Haskell](https://gitlab.com/frankhjung1/haskell-wordpuzzle)
* [WordPuzzle - Java](https://gitlab.com/frankhjung1/java-wordpuzzle)