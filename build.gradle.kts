plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "rol"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("rol.MainKt")
}

// Para que readln() funcione también con la tarea run de Gradle
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
