package com.kushwaha.MyTasks.Retrofit

import com.kushwaha.MyTasks.RoomDb.Task
import retrofit2.http.GET

interface TaskApi {
    @GET("todos")
    suspend fun getTasks(): List<Task>
}