import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.create

fun DependencyHandlerScope.okHttp() = create("com.squareup.okhttp3", "okhttp", Versions.OkHttp)

fun DependencyHandlerScope.gson() = create("com.google.code.gson", "gson", Versions.Gson)
