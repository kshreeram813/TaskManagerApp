package com.kushwaha.jepackcomposepractise

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.*
import com.kushwaha.jepackcomposepractise.Retrofit.RetrofitInstance
import com.kushwaha.jepackcomposepractise.RoomDb.Task
import com.kushwaha.jepackcomposepractise.RoomDb.TaskDatabase
import com.kushwaha.jepackcomposepractise.RoomDb.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val taskViewModel: TaskViewModel = viewModel()
                TaskScreen(viewModel = taskViewModel)
            }
        }
    }
}

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val database = Room.databaseBuilder(
        application,
        TaskDatabase::class.java, "task_database"
    ).build()
    private val repository = TaskRepository(database.taskDao())
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            ///
            fetchTasksFromApi()
            ///
            repository.getTasks().collectLatest { _tasks.value = it }
        }
    }
    //////////
    private suspend fun fetchTasksFromApi() {
        try {
            val apiTasks = RetrofitInstance.api.getTasks()
//            repository.insertTask(apiTasks)
        } catch (e: Exception) {
            // Handle API failure
        }
    }
    /////

    fun addTask(title: String) {
        viewModelScope.launch {
            val task = Task(title = title, completed = false)
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}

