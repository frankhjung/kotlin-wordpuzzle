import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.diffplug.spotless") version "5.12.4"
  id("com.github.johnrengelman.shadow") version "7.0.0"
  kotlin("jvm") version "1.5.0"
  application
}

group = "frankhjung"
version = "0.0.1"

java.sourceCompatibility = JavaVersion.VERSION_11

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
  kotlin() {
    // ktfmt("0.24") // problems on gitlab
    target("**/*.kt")
    ktlint().userData(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
  }
  kotlinGradle() {
    target("**/*.gradle.kts")
    ktlint().userData(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
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
        "Implementation-Version" to project.version
      )
    )
  }
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString() }

tasks.withType<Test> { useJUnitPlatform() }
