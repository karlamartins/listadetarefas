package com.example.listadetarefas.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.purple400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppBar() = TopAppBar(
  colors = TopAppBarDefaults.topAppBarColors(
    containerColor = purple400,
    titleContentColor = White,
  ),
  title = {
    Text(
      text = "Editar tarefas",
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold,
    )
  }
)