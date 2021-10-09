import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(gradleApi())
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("com.google.code.gson:gson:2.8.8")
}

publishing {
    publications {
        create<MavenPublication>("lokalise-loader-publish") {
            group = "com.github.Orangesoft-Development.trueddd"
            artifactId = "lokalise-loader"
            version = "0.1.5"
            from(components["java"])
        }
    }
}