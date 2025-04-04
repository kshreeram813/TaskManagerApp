package com.kushwaha.jepackcomposepractise

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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.completed) Color.Green.copy(alpha = 0.3f) else Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = {
                    viewModel.updateTask(task.copy(completed = !task.completed))
                }
            )

            Text(
                text = task.title,
                modifier = Modifier.weight(1f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = if (task.completed) Color.Gray else Color.Black,
                style = if (task.completed) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle()
            )

            IconButton(onClick = { onRenameClick(task) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.Blue)
            }
            IconButton(onClick = { viewModel.deleteTask(task) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }
        }
    }
}
