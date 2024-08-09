package com.example.listadetarefas.view;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listadetarefas.components.Button
import com.example.listadetarefas.components.InputText
import com.example.listadetarefas.constant.Constants
import com.example.listadetarefas.ui.theme.laranja
import com.example.listadetarefas.ui.theme.verde
import com.example.listadetarefas.ui.theme.vermelho

@Composable
fun DetailScreen(
  title: String,
  onTitleChanged: (String) -> Unit,
  description: String,
  onDescriptionChanged: (String) -> Unit,
  priority: Int,
  onPriorityChanged: (Int) -> Unit,
  onSaveClicked: () -> Unit,
  ) = Column(
  modifier = Modifier
    .fillMaxSize()
    .verticalScroll(rememberScrollState())
    .padding(20.dp, 80.dp, 20.dp, 0.dp)
) {
  InputText(
    value = title,
    onValueChange = { onTitleChanged(it) },
    modifier = Modifier
      .fillMaxWidth()
      .height(56.dp),
    label = "Titulo Tarefas",
    maxLines = 1,
  )
  InputText(
    value = description,
    onValueChange = { onDescriptionChanged(it) },
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
      onClick = { onPriorityChanged(Constants.LOW_PRIORITY) },
      colors = RadioButtonDefaults.colors(
        unselectedColor = verde,
        selectedColor = verde
      )
    )

    RadioButton(
      selected = priority == Constants.MEDIUM_PRIORITY,
      onClick = { onPriorityChanged(Constants.MEDIUM_PRIORITY) },
      colors = RadioButtonDefaults.colors(
        unselectedColor = laranja,
        selectedColor = laranja
      )
    )

    RadioButton(
      selected = priority == Constants.HIGH_PRIORITY,
      onClick = { onPriorityChanged(Constants.HIGH_PRIORITY) },
      colors = RadioButtonDefaults.colors(
        unselectedColor = vermelho,
        selectedColor = vermelho
      )
    )

  }
  Button(
    onClick = onSaveClicked,
    modifier = Modifier
      .fillMaxWidth()
      .height(80.dp)
      .padding(20.dp),
  )
}
