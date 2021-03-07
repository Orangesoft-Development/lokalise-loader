interface LokaliseLoader {

    fun load()

    companion object {
        fun create(block: Config.() -> Unit): LokaliseLoader {
            val config = Config().apply(block)
            return LokaliseLoaderImpl(
                config.apiToken ?: throw IllegalStateException("API token must be specified"),
                config.projectId ?: throw IllegalStateException("Project ID must be specified"),
                config.outputDirPath ?: throw IllegalStateException("Output directory must be specified"),
                config.platforms,
            )
        }
    }
}