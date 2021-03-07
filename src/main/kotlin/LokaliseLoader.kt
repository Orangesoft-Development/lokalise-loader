interface LokaliseLoader {

    fun load()

    companion object {
        fun create(apiToken: String, projectId: String, outputDirPath: String): LokaliseLoader {
            return LokaliseLoaderImpl(apiToken, projectId, outputDirPath)
        }
    }
}