package data

import com.google.gson.annotations.SerializedName

data class ProjectsResponse(
    @SerializedName("projects")
    val projects: List<Project>
)
