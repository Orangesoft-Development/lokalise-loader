import data.Language
import data.Platforms
import exceptions.LokaliseLoadException
import exceptions.ParameterNotSpecifiedException
import exceptions.ResourceWriteException

interface LokaliseLoader {

    /**
     * Loads string resources from Lokalise, formats them according Android standards and writes them into files.
     * @throws LokaliseLoadException if exception occurred while loading data from Lokalise
     * @throws ResourceWriteException if exception occurred while writing string resources into files
     */
    fun load()

    /**
     * Returns list of languages supported in current Lokalise project
     */
    fun loadLanguages(): List<Language>

    data class Config(
        var apiToken: String? = null,
        var projectId: String? = null,
        var outputDirPath: String? = null,
        var defaultLocale: String = "en",
        var platforms: List<Platforms> = listOf(Platforms.Android),
        var keys: List<String> = emptyList(),
    )

    companion object {
        fun create(block: Config.() -> Unit): LokaliseLoader {
            val config = Config().apply(block)
            return LokaliseLoaderImpl(
                config.apiToken ?: throw ParameterNotSpecifiedException("API token"),
                config.projectId ?: throw ParameterNotSpecifiedException("Project ID"),
                config.outputDirPath,
                config.platforms,
                config.defaultLocale,
                config.keys,
            )
        }
    }
}