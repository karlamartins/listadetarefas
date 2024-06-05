package com.example.listadetarefas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
import com.example.listadetarefas.ui.theme.purple400

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
  navController: NavController,
) {
  val context = LocalContext.current
  val taskRepository = TaskRepository()

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
    }
  ) {
    val taskList = taskRepository.recoverTask().collectAsState(mutableListOf()).value
    LazyColumn(
      modifier = Modifier.padding(top = 60.dp)
    ) {
      itemsIndexed(taskList) { position, _ ->
        TaskItem(
          position = position,
          taskList = taskList,
          context = context,
          navController = navController
        )
      }
    }
  }
}
