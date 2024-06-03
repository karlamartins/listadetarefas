package com.example.listadetarefas.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.azul
import com.example.listadetarefas.ui.theme.black

@Composable
fun InputText(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  modifier: Modifier,
  maxLines: Int,
) {
  OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    label = { Text(text = label) },
    maxLines = maxLines,
    colors = TextFieldDefaults.colors(
      focusedTextColor = black,
      focusedLabelColor = azul,
      cursorColor = azul,
      focusedIndicatorColor = azul,
      focusedContainerColor = White
    ),
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Text
    )
  )
}

@Composable
@Preview
fun InputTextPreview() {
  InputText(
    value = "Texto",
    onValueChange = {},
    modifier = Modifier,
    label = "titulo tarefa",
    maxLines = 1,
  )
}