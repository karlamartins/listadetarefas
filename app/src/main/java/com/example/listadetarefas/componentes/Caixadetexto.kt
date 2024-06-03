package com.example.listadetarefas.componentes

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

fun Caixadetexto (

    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType,

    ){

    OutlinedTextField(
        value = value ,
        onValueChange,
        label = { Text(text = label)

        },

        maxLines = maxLines,
        colors = TextFieldDefaults.colors(

            focusedTextColor = black,
            focusedLabelColor = azul,
            cursorColor = azul,
            focusedIndicatorColor = azul,
            focusedContainerColor = White





        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )

    )

}
@Composable
@Preview
fun CaixadetextoPreview() {
    Caixadetexto(
        value = "Texto",
        onValueChange = {},
        modifier = Modifier,
        label = "titulo tarefa",
        maxLines = 1,
        keyboardType = KeyboardType.Text
    )
}