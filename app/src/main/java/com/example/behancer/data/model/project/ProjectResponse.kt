package com.example.behancer.data.model.project

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProjectResponse(
    @SerializedName("projects")
    var projects: List<Project>
) : Serializable