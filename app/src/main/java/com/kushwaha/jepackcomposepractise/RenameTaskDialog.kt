package com.kushwaha.MyTasks

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kushwaha.MyTasks.RoomDb.Task

@Composable
fun RenameTaskDialog(task: Task, onDismiss: () -> Unit, onRename: (String) -> Unit) {
    var newTitle by remember { mutableStateOf(task.title) }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { Button(onClick = { onRename(newTitle) }) { Text("Rename") } },
        dismissButton = { Button(onClick = onDismiss) { Text("Cancel") } },
        title = { Text("Rename Task") },
        text = { OutlinedTextField(value = newTitle, onValueChange = { newTitle = it }) }
    )
}