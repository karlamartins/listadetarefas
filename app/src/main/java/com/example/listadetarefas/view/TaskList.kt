package com.example.listadetarefas.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
  navController: NavController,
  taskRepository: TaskRepository,
) {
  var searchText by remember { mutableStateOf("") }
  val taskList by taskRepository.recoverTask().collectAsState(initial = emptyList())
  val context = LocalContext.current

  Scaffold(
    topBar = {

      Column {

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
        SearchBar(
          modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .semantics { traversalIndex = 0f },
          inputField = {
            SearchBarDefaults.InputField(

              query = searchText,
              onQueryChange = { newText ->
                searchText = newText
              },
              onSearch = {},
              expanded = false,
              onExpandedChange = {},
              placeholder = { Text("Pesquisar") },
              leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )
          },
          expanded = false,
          onExpandedChange = {},
        ) {}

      }
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
    content = { padding ->
     val filteredTasks = if (searchText.isNotEmpty()) {
      taskList.filter { it.title!!.contains(searchText, ignoreCase = true) }
    } else {
      taskList
    }

      LazyColumn(
        modifier = Modifier.padding(top = 160.dp)
      ) {
        itemsIndexed(filteredTasks) { position, _ ->
          TaskItem(
            position = position,
            taskList = filteredTasks.toMutableList(),
            context = context,
            navController = navController,
            taskRepository = taskRepository,

            )
        }
      }
    }
  )
}


