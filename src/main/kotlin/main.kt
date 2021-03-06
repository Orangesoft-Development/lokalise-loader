fun main(args: Array<String>) {
    val apiToken = args[0]
    val projectId = args[1]
    val output = args[2]
    val loader = LokaliseLoader(apiToken, projectId, output)
    loader.test()
    loader.load()
}