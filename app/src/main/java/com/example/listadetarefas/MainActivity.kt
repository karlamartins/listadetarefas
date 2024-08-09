package com.example.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadetarefas.model.Task
import com.example.listadetarefas.repositorio.TaskRepository
import com.example.listadetarefas.ui.theme.TaskListTheme
import com.example.listadetarefas.view.TaskList
import com.example.listadetarefas.view.CreateTask
import com.example.listadetarefas.view.EditTask
import com.example.listadetarefas.view.FilterScreen

class MainActivity : ComponentActivity() {

  private val taskRepository = TaskRepository()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TaskListTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "TaskList") {
          composable(
            route = "TaskList"
          ) {
            TaskList(navController, taskRepository)
          }
          composable(
            route = "CreateTask"
          ) {
            CreateTask(navController, taskRepository)
          }
          composable(
            route = "Filter"
          ) {
            FilterScreen(navController, taskRepository)
          }
          composable(
            route = "EditTask/{taskId}"
          ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            EditTask(navController, taskRepository, taskId)
          }
        }
      }
    }
  }
}

