package com.example.eestech_challenge_begemol.Retrofit

import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.Model.Question
import com.example.eestech_challenge_begemol.Model.Task
import com.example.eestech_challenge_begemol.Model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("user")
    suspend fun getUserById(@Query("userId") id: String): Response<User>

    @GET("group")
    suspend fun getCategories(): Response<List<Category>>

    @GET("question")
    suspend fun getQuestions(@Query("taskId") id: String): Response<List<Question>>

    @GET("group/tasks")
    suspend fun getTask(@Query("groupId") id: String): Response<List<Task>>
}