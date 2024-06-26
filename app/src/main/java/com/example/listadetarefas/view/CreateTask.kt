package com.example.listadetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefas.components.Button
import com.example.listadetarefas.components.InputText
import com.example.listadetarefas.constant.Constants
import com.example.listadetarefas.model.Task
import com.example.listadetarefas.repositorio.TaskRepository
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.laranja
import com.example.listadetarefas.ui.theme.purple400
import com.example.listadetarefas.ui.theme.verde
import com.example.listadetarefas.ui.theme.vermelho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTask(
  navController: NavController,
  taskRepository: TaskRepository,
) {
  val scope = rememberCoroutineScope()
  val context = LocalContext.current

  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = purple400,
          titleContentColor = White,
        ),
        title = {
          Text(
            text = "Salvar tarefas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
          )
        }
      )
    }
  ) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Constants.NO_PRIORITY) }

    Column(
      modifier = Modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
          .padding(20.dp, 80.dp, 20.dp, 0.dp)
    ) {
      InputText(
        value = title,
        onValueChange = { title = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        label = "Titulo Tarefas",
        maxLines = 1,
      )
      InputText(
        value = description,
        onValueChange = { description = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        label = "Descriçao Tarefas",
        maxLines = 5,
      )
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "Nivel de Prioridade",
          fontSize = 20.sp
        )

        RadioButton(
          selected = priority == Constants.LOW_PRIORITY,
          onClick = { priority = Constants.LOW_PRIORITY },
          colors = RadioButtonDefaults.colors(
            unselectedColor = verde,
            selectedColor = verde
          )
        )

        RadioButton(
          selected = priority == Constants.MEDIUM_PRIORITY,
          onClick = { priority = Constants.MEDIUM_PRIORITY },
          colors = RadioButtonDefaults.colors(
            unselectedColor = laranja,
            selectedColor = laranja
          )
        )

        RadioButton(
          selected = priority == Constants.HIGH_PRIORITY,
          onClick = { priority = Constants.HIGH_PRIORITY },
          colors = RadioButtonDefaults.colors(
            unselectedColor = vermelho,
            selectedColor = vermelho
          )
        )

      }
      Button(
        onClick = {
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
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(20.dp),
      )
    }
  }
}