package tasks

import LokaliseLoader
import data.Platforms
import exceptions.LokaliseException
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

@Suppress("unused")
open class LokaliseUpdateTask : DefaultTask() {

    init {
        group = "lokalise"
    }

    @Input
    lateinit var targetDir: String
    @Input
    lateinit var apiToken: String
    @Input
    lateinit var projectId: String
    @Input
    var platforms: List<Platforms> = listOf(Platforms.Android)
    @Input
    var defaultLocaleIso: String = "en"
    @Input
    var keys: List<String> = emptyList()

    @TaskAction
    fun updateLokalise() = try {
        val lokaliseLoader = LokaliseLoader.create {
            apiToken = this@LokaliseUpdateTask.apiToken
            projectId = this@LokaliseUpdateTask.projectId
            outputDirPath = targetDir
            platforms = this@LokaliseUpdateTask.platforms
            defaultLocale = defaultLocaleIso
            keys = this@LokaliseUpdateTask.keys
        }
        lokaliseLoader.load()
    } catch (e: LokaliseException) {
        throw GradleException(e.message, e)
    }
}
