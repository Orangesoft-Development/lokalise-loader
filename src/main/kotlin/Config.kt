data class Config(
    var apiToken: String? = null,
    var projectId: String? = null,
    var outputDirPath: String? = null,
    var platforms: List<String> = listOf("android"),
)
