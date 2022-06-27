import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.Kotlin
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(gradleApi())
    implementation(okHttp())
    implementation(gson())
}

publishing {
    publications {
        create<MavenPublication>("lokalise-loader-publish") {
            group = "com.github.${Config.GithubOrganisation}"
            artifactId = Config.ArtifactId
            version = Config.Version
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "LocalTest"
            url = uri(layout.buildDirectory.dir("snapshots"))
        }
    }
}