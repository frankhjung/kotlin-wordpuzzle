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

## Build

To build this project with Gradle [Kotlin
DLS](https://docs.gradle.org/current/userguide/kotlin_dsl.html), run

```bash
./gradlew spotlessApply compileKotlin
```

This will format code and build the Kotlin package.

To build a fat JAR I am using
[Gradle Shadow](https://imperceptiblethoughts.com/shadow/)

## Examples

### Help

Print program help message using one of:

```bash
./gradlew run --args='-h'
kotlin -cp build/libs/games-1.0.0-all.jar com.marlo.games.MainKt -h
java -cp build/libs/games-1.0.0-all.jar com.marlo.games.MainKt -h
```

This will print the help message:

For example:

```bash
$ java -cp build/libs/games-1.0.0-all.jar com.marlo.games.MainKt -h
Usage: wordpuzzle options_list
Options:
    --dictionary, -d [dictionary/british] -> dictionary to use in word search { String }
    --size, -s [4] -> minimum word size { Int }
    --mandatory, -m -> mandatory character (always required) { String }
    --letters, -l -> letters to create words from (always required) { String }
    --help, -h -> Usage info
```

### Run

Three ways to run puzzle solution:

```bash
./gradlew run --args="-m c -l adevcrsoi"

kotlin -cp build/libs/games-1.0.0-all.jar com.marlo.games.MainKt -m c -l adevcrsoi

java -cp build/libs/games-1.0.0-all.jar com.marlo.games.MainKt -m c -l adevcrsoi
```

## References

* [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
* [Kotlin API Documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/)
