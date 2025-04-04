package com.kushwaha.jepackcomposepractise

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kushwaha.jepackcomposepractise.RoomDb.Task

@Composable
fun TaskItem(task: Task, viewModel: TaskViewModel, onRenameClick: (Task) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.completed, onCheckedChange = {
                viewModel.updateTask(task.copy(completed = !task.completed))
            })
            Text(text = task.title, modifier = Modifier.weight(1f), fontSize = 18.sp)
            DropdownMenuTask(task, viewModel, onRenameClick)
        }
    }
}