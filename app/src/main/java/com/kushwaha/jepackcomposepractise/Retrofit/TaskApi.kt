package com.kushwaha.jepackcomposepractise.Retrofit

import com.kushwaha.jepackcomposepractise.RoomDb.Task
import retrofit2.http.GET

interface TaskApi {
    @GET("todos")
    suspend fun getTasks(): List<Task>
}