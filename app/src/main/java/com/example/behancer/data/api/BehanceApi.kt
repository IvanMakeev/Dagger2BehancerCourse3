package com.example.behancer.data.api

import com.example.behancer.data.model.project.ProjectResponse
import com.example.behancer.data.model.user.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BehanceApi {

    @GET("v2/projects")
    fun getProjects(@Query("q") query: String): Single<ProjectResponse>

    @GET("v2/users/{username}")
    fun getUserInfo(@Path("username") username: String): Single<UserResponse>

    @GET("/v2/users/{user}/projects")
    fun getUserProjects(@Path("user") username: String) : Single<ProjectResponse>

}