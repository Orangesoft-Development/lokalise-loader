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
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/${Config.GithubOrganisation}/${Config.ArtifactId}")

            credentials {
                username = project.properties["gpr.usr"]?.toString() ?: System.getenv("GPR_USER")
                password = project.properties["gpr.key"]?.toString() ?: System.getenv("GPR_API_KEY")
            }
        }
    }
}