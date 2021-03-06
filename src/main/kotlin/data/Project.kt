package data

import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("project_id")
    val projectId: String,
    @SerializedName("name")
    val name: String,
)
