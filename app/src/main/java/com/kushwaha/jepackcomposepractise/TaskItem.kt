package com.kushwaha.jepackcomposepractise

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kushwaha.jepackcomposepractise.RoomDb.Task

@Composable
fun TaskItem(task: Task, viewModel: TaskViewModel, onRenameClick: (Task) -> Unit) {
    val cardBackgroundColor = if (task.completed) Color(0xFF80CBC4) else Color.White // Light teal for completed tasks

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight() // Make the card height flexible based on content
            .animateContentSize(), // Automatically resize the card when content changes
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Light elevation
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp) // Padding around the row inside the card
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox for task completion
            Checkbox(
                checked = task.completed,
                onCheckedChange = {
                    viewModel.updateTask(task.copy(completed = !task.completed))
                },
                modifier = Modifier.padding(end = 12.dp) // Space between checkbox and text
            )

            // Task title
            Text(
                text = task.title,
                modifier = Modifier.weight(1f),
                fontSize = if (task.completed) 16.sp else 14.sp, // Slightly larger font for completed tasks
                fontWeight = if (task.completed) FontWeight.Bold else FontWeight.Normal,
                color = if (task.completed) Color.White else Color.Black, // White text for completed tasks
                style = if (task.completed) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle()
            )

            // Edit button
            IconButton(onClick = { onRenameClick(task) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.Blue)
            }

            // Delete button
            IconButton(onClick = { viewModel.deleteTask(task) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }
        }
    }
}
