fun main(args: Array<String>) {
    val apiToken = args[0]
    val projectId = args[1]
    val output = args[2]
    val loader = LokaliseLoaderImpl(apiToken, projectId, output)
    loader.test()
    loader.load()
}