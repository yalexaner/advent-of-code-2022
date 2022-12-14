plugins {
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}

tasks.register("runDay1", JavaExec::class) {
    mainClass.set("Day1Kt")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register("runDay2", JavaExec::class) {
    mainClass.set("Day2Kt")
    classpath = sourceSets["main"].runtimeClasspath
}