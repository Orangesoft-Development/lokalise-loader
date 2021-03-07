import data.Platforms

interface LokaliseLoader {

    fun load()

    data class Config(
        var apiToken: String? = null,
        var projectId: String? = null,
        var outputDirPath: String? = null,
        var defaultLocale: String = "en",
        var platforms: List<Platforms> = listOf(Platforms.Android),
    )

    companion object {
        fun create(block: Config.() -> Unit): LokaliseLoader {
            val config = Config().apply(block)
            return LokaliseLoaderImpl(
                config.apiToken ?: throw IllegalStateException("API token must be specified"),
                config.projectId ?: throw IllegalStateException("Project ID must be specified"),
                config.outputDirPath ?: throw IllegalStateException("Output directory must be specified"),
                config.platforms,
                config.defaultLocale,
            )
        }
    }
}