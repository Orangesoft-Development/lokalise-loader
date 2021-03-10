package util

import okhttp3.HttpUrl

fun createBaseUrlBuilder(): HttpUrl.Builder {
    return HttpUrl.Builder()
        .scheme("https")
        .host("api.lokalise.com")
        .addPathSegment("api")
}