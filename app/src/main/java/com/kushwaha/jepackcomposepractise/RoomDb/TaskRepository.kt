package com.kushwaha.MyTasks.RoomDb

class TaskRepository(private val taskDao: TaskDao) {
    fun getTasks() = taskDao.getAllTasks()
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}