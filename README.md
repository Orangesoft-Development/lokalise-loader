Kotlin library built to provide an easy way of pulling actual Android string resources from [Lokalise](https://lokalise.com/).

[![](https://jitpack.io/v/Orangesoft-Development/lokalise-loader.svg)](https://jitpack.io/#Orangesoft-Development/lokalise-loader)

Add maven repository to your Gradle file:
```kotlin
// kotlin gradle dsl
repositories {
    maven { url = uri("https://jitpack.io/") }
}
```

Add library definition to dependencies:
```kotlin
// kotlin gradle dsl
implementaion("com.github.Orangesoft-Development:lokalise-loader:<version>")
```

Library provides Gradle task to load string resources during builds. To use task register it like in sample below:
```kotlin
// kotlin gradle dsl
task<LokaliseUpdateTask>("lokalise-update") {
    targetDir = "<resources-directory-path>"
    apiToken = "<lokalise-user-api-key>"
    projectId = "<lokalise-projectId>"
    platforms = listOf(data.Platforms.Android)
}
```

Or use `LokaliseLoader` directly using `create` method:
```kotlin
val lokaliseLoader = LokaliseLoader.create {
    apiToken = "<lokalise-user-api-key>"
    projectId = "<lokalise-projectId>"
    outputDirPath = "<resources-directory-path>"
    platforms = listOf(data.Platforms.Android)
}
lokaliseLoader.load()
```
