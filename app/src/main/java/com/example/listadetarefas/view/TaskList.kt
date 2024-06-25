package com.example.listadetarefas.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefas.R
import com.example.listadetarefas.itemlist.TaskItem
import com.example.listadetarefas.repositorio.TaskRepository
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.black
import com.example.listadetarefas.ui.theme.laranja
import com.example.listadetarefas.ui.theme.purple400
import com.example.listadetarefas.ui.theme.verde

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
  navController: NavController,
  taskRepository: TaskRepository,
) {

  val context = LocalContext.current
  val selectedFilters = navController.currentBackStackEntry?.arguments?.getString("filters")?.split(",")?.map { Priority.valueOf(it) } ?: emptyList()
  val tasks = taskRepository.recoverTask().collectAsState()


  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = purple400,
          titleContentColor = White,
        ),
        title = {
          Text(
            "Lista de Tarefas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
          )
        },
        actions = {
          IconButton(onClick = {
             navController.navigate("Filter")
          }) {
            Icon(
              imageVector = ImageVector.vectorResource(id = R.drawable.baseline_filter_list_24),
              contentDescription = "filtro",
              tint = White
            )
          }
        }
      )
    },
    containerColor = black,
    floatingActionButton = {
      FloatingActionButton(
        onClick = { navController.navigate("CreateTask") },
        containerColor = purple400
      ) {
        Image(
          imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_24),
          contentDescription = "Icone de salvar tarefas"
        )
      }
    },
    content =  { padding ->
      val taskList = taskRepository.recoverTask().collectAsState(mutableListOf()).value
      LazyColumn(
        modifier = Modifier.padding(top = 60.dp)
      ) {
        if (tasks.value.isNotEmpty()) {
          val filteredTasks = tasks.value.filter { task ->
            selectedFilters.isEmpty() || selectedFilters.contains(task.priority)
          }
          TaskListContent(filteredTasks = filteredTasks)
        } else {
          Text(text = "Nenhuma tarefa encontrada", fontSize = 20.sp)
        }
      }
    }
  )
}@Composable
private fun TaskListContent(filteredTasks: List<Task>) {
  Column {
    filteredTasks.forEachIndexed { index, task ->
      TaskItem(task = task, index = index + 1)
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@Composable
private fun TaskItem(task: Task, index: Int) {

  Text(
    text = "Tarefa $index: ${task.title} - ${task.description} (${task.priority.name})",
    style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp)
  )
}
