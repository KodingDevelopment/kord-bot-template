import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
}

group = "dev.koding"
version = "1.0.0"

val main = "dev.koding.template.AppKt"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.kotlindiscord.com/repository/maven-public/")
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC")

    // Kord
    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.5.6-SNAPSHOT")

    // Logging
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
}

// Java build
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}

tasks.jar {
    manifest.attributes(
        "Main-Class" to main
    )
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks["build"].dependsOn(tasks.shadowJar)

// IDEA
idea {
    project {
        settings {
            runConfigurations {
                create<org.jetbrains.gradle.ext.Application>("Run Bot") {
                    mainClass = main
                    moduleName = "${rootProject.name}.main"

                    val workDir = rootProject.file("run").also { it.mkdirs() }
                    workingDirectory = workDir.absolutePath
                }
            }
        }
    }
}
