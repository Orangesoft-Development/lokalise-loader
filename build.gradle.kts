import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    application
}

group = "com.github.Orangesoft-Development.trueddd"
version = "0.1.4"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}

dependencies {
    implementation(gradleApi())
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("com.google.code.gson:gson:2.8.8")
}