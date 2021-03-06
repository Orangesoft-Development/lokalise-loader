import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

class LokaliseLoader(
    private val apiToken: String,
    private val projectId: String,
    private val outputDirPath: String,
) {

    private val okHttpClient by lazy { OkHttpClient() }

    private val gson by lazy { Gson() }

    private inline fun <reified T> Gson.parseData(raw: String): T? {
        return try {
            fromJson(raw, object : TypeToken<T>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun test() {
        println("$apiToken $projectId $outputDirPath")
    }

    private fun loadKeys(): List<Key> {
        val keysBaseUrl = "https://api.lokalise.com/api2/projects/$projectId/keys?include_comments=0&include_screenshots=0&include_translations=0&filter_platforms=android"
        val keysRequest = Request.Builder()
            .get()
            .addHeader("X-Api-Token", apiToken)
        val keys = mutableListOf<Key>()
        var page = 1
        while (true) {
            val call = okHttpClient.newCall(
                keysRequest
                    .url("$keysBaseUrl&page=$page&limit=5000")
                    .build()
            ).execute()
            val pageKeys = if (call.isSuccessful) {
                call.body?.string()?.let { gson.parseData<KeysResponse>(it) }
            } else {
                println("error: ${call.message}")
                null
            }
            when {
                pageKeys == null -> throw IllegalStateException("Something wrong happened while loading keys")
                pageKeys.keys.isEmpty() -> break
                else -> {
                    keys.addAll(pageKeys.keys)
                    page++
                }
            }
        }
        println("Loaded ${keys.size} keys")
        return keys
    }

    private fun loadLanguages(): List<Language> {
        val baseUrl = "https://api.lokalise.com/api2/projects/$projectId/languages?limit=5000"
        val request = Request.Builder()
            .get()
            .url(baseUrl)
            .addHeader("X-Api-Token", apiToken)
            .build()
        val call = okHttpClient.newCall(request).execute()
        val response = if (call.isSuccessful) {
            call.body?.string()?.let { gson.parseData<LanguageResponse>(it) }
        } else {
            println("error: ${call.message}")
            null
        } ?: throw IllegalStateException("Something wrong happened while loading languages")
        println("Loaded ${response.languages.size} languages")
        return response.languages
    }

    private fun loadTranslations(): List<Translation> {
        val baseUrl = "https://api.lokalise.com/api2/projects/$projectId/translations"
        val translationsRequest = Request.Builder()
            .get()
            .addHeader("X-Api-Token", apiToken)
        val allTranslations = mutableListOf<Translation>()
        var page = 1
        while (true) {
            val call = okHttpClient.newCall(
                translationsRequest
                    .url("$baseUrl?page=$page&limit=5000")
                    .build()
            ).execute()
            val translations = if (call.isSuccessful) {
                call.body?.string()?.let { gson.parseData<TranslationsResponse>(it) }
            } else {
                println("error: ${call.message}")
                null
            }
            when {
                translations == null -> throw IllegalStateException("Something wrong happened while loading translations")
                translations.translations.isEmpty() -> break
                else -> {
                    allTranslations.addAll(translations.translations)
                    page++
                }
            }
        }
        println("Loaded ${allTranslations.size} translations")
        return allTranslations
    }

    private fun writeKeysToFile(file: File, keys: List<Key>) {
        val xmlFormattedKeys = keys.map { "<string name=\"${it.keyName.android}\"></string>" }
        val xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        val result = buildString {
            append(xmlHeader)
            append("\n<resources>")
            xmlFormattedKeys.forEach {
                append("\n\t$it")
            }
            append("\n</resources>")
        }
        file.writeText(result)
    }

    fun load() {
        val languages = loadLanguages()
        val keys = loadKeys()
//        val translations = loadTranslations()
        println(languages)
        val isoLangs = languages.map { it.langIso.substringBefore('_') }
        val dirNames = isoLangs.filter { it == "ru" }.map { "values-$it" }
        dirNames.forEach {
            val dir = File("$outputDirPath/$it")
            if (!dir.exists()) {
                dir.mkdir()
            }
            val file = File("$outputDirPath/$it/strings.xml")
            file.delete()
            file.createNewFile()
            writeKeysToFile(file, keys)
        }
    }
}