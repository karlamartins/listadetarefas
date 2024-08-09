package com.example.listadetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTask(
  navController: NavController,
  taskRepository: TaskRepository,
) {
  val scope = rememberCoroutineScope()
  val context = LocalContext.current

  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var priority by remember { mutableStateOf(Constants.NO_PRIORITY) }

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

        scope.launch(Dispatchers.IO) {
          if (hasRequiredFields) {
            taskRepository.saveTask(Task(title, description, priority))
          }
        }

        scope.launch(Dispatchers.Main) {
          if (hasRequiredFields) {
            Toast.makeText(context, "Sucesso ao salvar tarefa", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
          } else {
            Toast.makeText(
              context,
              "Titulo e descrição da tarefa são obrigatórios ",
              Toast.LENGTH_SHORT
            ).show()
          }
        }
      }
    )
  }
}