import data.Platforms

fun main(args: Array<String>) {
    val apiToken = args[0]
    val projectId = args[1]
    val output = args[2]
    val loader = LokaliseLoader.create {
        this.apiToken = apiToken
        this.projectId = projectId
        this.outputDirPath = output
        this.platforms = listOf(Platforms.Android, Platforms.Other)
    }
    loader.load()
}