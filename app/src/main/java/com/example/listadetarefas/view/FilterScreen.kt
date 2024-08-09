package com.example.listadetarefas.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefas.constant.Constants.ALL_PRIORITY
import com.example.listadetarefas.constant.Constants.HIGH_PRIORITY
import com.example.listadetarefas.constant.Constants.LOW_PRIORITY
import com.example.listadetarefas.constant.Constants.MEDIUM_PRIORITY
import com.example.listadetarefas.repositorio.TaskRepository
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.azul
import com.example.listadetarefas.ui.theme.purple400

@Composable
fun FilterScreen(
  navController: NavController,
  taskRepository: TaskRepository,
) {

  val (allPriorityChecked, setAllPriorityChecked) = remember {
    mutableStateOf(taskRepository.filters.contains(ALL_PRIORITY))
  }
  val (lowPriorityChecked, setLowPriorityChecked) = remember {
    mutableStateOf(taskRepository.filters.contains(LOW_PRIORITY))
  }
  val (mediumPriorityChecked, setMediumPriorityChecked) = remember {
    mutableStateOf(taskRepository.filters.contains(MEDIUM_PRIORITY))
  }
  val (highPriorityChecked, setHighPriorityChecked) = remember {
    mutableStateOf(taskRepository.filters.contains(HIGH_PRIORITY))
  }

  Box(
    modifier = Modifier.fillMaxSize()
  ) {
    Box(
      modifier = Modifier
          .fillMaxWidth()
          .height(65.dp)
          .background(purple400),
    ) {
      Text(
        text = "Filtro",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        style = TextStyle(color = White),
        modifier = Modifier.padding(20.dp)
      )
    }

    Column(
      modifier = Modifier
          .fillMaxSize()
          .padding(top = 60.dp)
          .padding(horizontal = 16.dp)
    ) {

      Spacer(modifier = Modifier.height(16.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Checkbox(
          checked = allPriorityChecked,
          onCheckedChange = { isChecked ->
            setAllPriorityChecked (isChecked)
            setLowPriorityChecked(isChecked)
            setMediumPriorityChecked(isChecked)
            setHighPriorityChecked(isChecked)
          },
          modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
          text = "Todas Prioridades",
          modifier = Modifier.weight(1f)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Checkbox(
          checked = lowPriorityChecked,
          onCheckedChange = { checked ->
            setLowPriorityChecked(checked)
              if (!checked) {
                  setAllPriorityChecked(false)
              } else if (highPriorityChecked && mediumPriorityChecked){
                  setAllPriorityChecked(true)
              }

          },
          modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
          text = "Baixa Prioridade",
          modifier = Modifier.weight(1f)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Checkbox(
          checked = mediumPriorityChecked,
          onCheckedChange = { checked ->
            setMediumPriorityChecked(checked)
              if (!checked) {
                  setAllPriorityChecked(false)
              } else if (lowPriorityChecked && highPriorityChecked){
                  setAllPriorityChecked(true)
              }
          },
          modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
          text = "Média Prioridade",
          modifier = Modifier.weight(1f)
        )
      }

      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Checkbox(
          checked = highPriorityChecked,
          onCheckedChange = { checked ->
            setHighPriorityChecked(checked)
              if (!checked) {
                  setAllPriorityChecked(false)
              } else if (lowPriorityChecked && mediumPriorityChecked){
                  setAllPriorityChecked(true)
              }
          },
          modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
          text = "Alta Prioridade",
          modifier = Modifier.weight(1f)
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        FloatingActionButton(
          onClick = {
            val selectedFilters = mutableListOf<Int>()
            if (allPriorityChecked) selectedFilters.add(ALL_PRIORITY)
            if (lowPriorityChecked) selectedFilters.add(LOW_PRIORITY)
            if (mediumPriorityChecked) selectedFilters.add(MEDIUM_PRIORITY)
            if (highPriorityChecked) selectedFilters.add(HIGH_PRIORITY)
            taskRepository.setFilter(selectedFilters)

            navController.popBackStack()
          },
          containerColor = azul,
          modifier = Modifier.size(120.dp, 45.dp),
        ) {
          Text(
            text = "Aplicar Filtro",
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold)
          )
        }

        FloatingActionButton(
          onClick = { navController.popBackStack() },
          containerColor = azul,
          modifier = Modifier.size(120.dp, 45.dp),
        ) {
          Text(
            text = "Cancelar",
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}