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
implementation("com.github.Orangesoft-Development:lokalise-loader:<version>")
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

Additionally, you can specify list of keys to load from Lokalise. This might be helpful if you want to load some translations in non-main module and prevent all resources to duplicated in main module.
```kotlin
task<LokaliseUpdateTask>("lokalise-update") {
    targetDir = "<resources-directory-path>"
    apiToken = "<lokalise-user-api-key>"
    projectId = "<lokalise-projectId>"
    platforms = listOf(data.Platforms.Android)
    keys = listOf(
        "my_first_string",
        "my_second_string",
    )
}
```
