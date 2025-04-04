package com.kushwaha.jepackcomposepractise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kushwaha.jepackcomposepractise.RoomDb.Task

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf(TextFieldValue()) }
    var showDialog by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            label = { Text("New Task") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if (newTaskTitle.text.isNotEmpty()) {
                    viewModel.addTask(newTaskTitle.text)
                    newTaskTitle = TextFieldValue()
                }
            })
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (newTaskTitle.text.isNotEmpty()) {
                    viewModel.addTask(newTaskTitle.text)
                    newTaskTitle = TextFieldValue()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(tasks.size) { index ->
                TaskItem(task = tasks[index], viewModel = viewModel, onRenameClick = {
                    currentTask = it
                    showDialog = true
                })
            }
        }
    }

    if (showDialog) {
        RenameTaskDialog(
            task = currentTask!!,
            onDismiss = { showDialog = false },
            onRename = { newTitle ->
                viewModel.updateTask(currentTask!!.copy(title = newTitle))
                showDialog = false
            }
        )
    }
}