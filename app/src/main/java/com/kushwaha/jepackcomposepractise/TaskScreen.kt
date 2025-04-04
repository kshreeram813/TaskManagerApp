package com.kushwaha.jepackcomposepractise

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kushwaha.jepackcomposepractise.RoomDb.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf(TextFieldValue()) }
    var showDialog by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFF6A1B9A))
                )
            )
            .padding(16.dp)
    ) {
        // Progress Bar
        val completedTasks = tasks.count { it.completed }
        val progress = if (tasks.isNotEmpty()) completedTasks.toFloat() / tasks.size else 0f

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(6.dp),
            color = Color.Green
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Input Field with Modern Look
        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            placeholder = { Text("Add a new task...", fontSize = 16.sp, color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
                .padding(4.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if (newTaskTitle.text.isNotEmpty()) {
                    viewModel.addTask(newTaskTitle.text)
                    newTaskTitle = TextFieldValue()
                }
            }),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Task List with Animation
        LazyColumn {
            items(tasks, key = { it.id }) { task ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    TaskItem(task = task, viewModel = viewModel, onRenameClick = {
                        currentTask = it
                        showDialog = true
                    })
                }
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