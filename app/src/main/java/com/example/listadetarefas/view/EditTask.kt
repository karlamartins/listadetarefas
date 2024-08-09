package com.example.listadetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.listadetarefas.constant.Constants
import com.example.listadetarefas.model.Task
import com.example.listadetarefas.repositorio.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun EditTask(
  navController: NavController,
  taskRepository: TaskRepository,
  taskId: String
) {
  val scope = rememberCoroutineScope()
  val context = LocalContext.current

  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var priority by remember { mutableStateOf(Constants.NO_PRIORITY) }

  LaunchedEffect(taskId) {
    val task = taskRepository.getTaskById(taskId)
    if (task != null) {
      title = task.title ?: ""
      description = task.description ?: ""
      priority = task.priority ?: Constants.NO_PRIORITY
    }
  }
  Scaffold(
    topBar = { CommonTopAppBar() }
  ) {
    DetailScreen(
      title = title,
      onTitleChanged = { title = it },
      description = description,
      onDescriptionChanged = { description = it },
      priority = priority,
      onPriorityChanged = { priority = it },
      onSaveClicked = {
        val hasRequiredFields = title.isNotEmpty() && description.isNotEmpty()

        if (hasRequiredFields) {
          val updatedTask =
            Task(id = taskId, title = title, description = description, priority = priority)
          scope.launch(Dispatchers.IO) {
            taskRepository.updateTask(updatedTask)
            launch(Dispatchers.Main) {
              Toast.makeText(context, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show()
              navController.popBackStack()
            }
          }
        } else {
          Toast.makeText(
            context,
            "Título e descrição da tarefa são obrigatórios",
            Toast.LENGTH_SHORT
          ).show()
        }
      }
    )
  }
}


