import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
  id("com.diffplug.spotless") version "6.25.0"
  id("com.gradleup.shadow") version "8.3.0"
  kotlin("jvm") version "2.3.0"
  application
}

group = "frankhjung"
version = "2.0.0"

kotlin {
  jvmToolchain(21)
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
  implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
  implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.2")
  implementation("org.slf4j:slf4j-simple:2.0.0-alpha1")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
}

/** Check that the build file is formatted correctly. */
spotless {
  format("misc") {
    target("**/*.gradle", "**/*.md", "**/.gitignore")
    trimTrailingWhitespace()
    indentWithSpaces(4)
  }
  kotlin {
    // ktfmt("0.24") // problems on gitlab
    target("**/*.kt")
    ktlint().editorConfigOverride(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktlint().editorConfigOverride(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
  }
  java { googleJavaFormat() }
}

application {
  mainClass.set("com.marlo.games.MainKt")
}

tasks.withType<Jar> {
  manifest {
    attributes(
      mapOf(
        "Main-Class" to application.mainClass,
        "Implementation-Title" to project.name,
        "Implementation-Version" to project.version,
      ),
    )
  }
}

// import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// tasks.withType<KotlinCompile> {
//  // default is version "1.8"
//  kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
// }

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events(FAILED, PASSED, STANDARD_ERROR, STANDARD_OUT, SKIPPED)
    exceptionFormat = FULL
    showExceptions = true
    showCauses = true
    showStackTraces = true
  }
}
