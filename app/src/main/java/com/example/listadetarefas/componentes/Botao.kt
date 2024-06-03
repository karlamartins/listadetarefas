package com.example.listadetarefas.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.azul

@Composable
fun Botao(

    onClick: () -> Unit,
    modifier: Modifier,
    texto: String,

    ){

    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = azul,
            contentColor = White

        )
    ) {
        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp)

    }

}