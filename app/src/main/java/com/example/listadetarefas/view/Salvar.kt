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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefas.componentes.Botao
import com.example.listadetarefas.componentes.Caixadetexto
import com.example.listadetarefas.constantes.Constantes
import com.example.listadetarefas.repositorio.Tarefasrepositorio
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
fun Salvar( navController: NavController
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasrepositorio = Tarefasrepositorio()


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = purple400,
                    titleContentColor = White,
                ),
                title = {
                    (Text(
                        text = "Salvar tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                            )
                }
            )
        }
    ) {
        var titulotarefa by remember { mutableStateOf("") }
        var descricaotarefa by remember { mutableStateOf("") }
        var semprioridadetarefa by remember { mutableStateOf(false) }
        var semprioridadebaixa by remember { mutableStateOf(false) }
        var semprioridademedia by remember { mutableStateOf(false) }
        var semprioridadealta by remember { mutableStateOf(false) }

        Column(

            modifier = Modifier

                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp, 110.dp, 20.dp, 0.dp)


        ) {
            Caixadetexto(
                value = titulotarefa,
                onValueChange = { titulotarefa = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                label = "titulo tarefas",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            Caixadetexto(
                value = descricaotarefa,
                onValueChange = { descricaotarefa = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                label = "descriçao tarefas",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Nivel de prioridade",
                    fontSize = 20.sp
                )

                RadioButton(
                    selected = semprioridadebaixa,
                    onClick = {

                        semprioridadebaixa = !semprioridadebaixa

                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = verde,
                        selectedColor = verde

                    )
                )


                RadioButton(
                    selected = semprioridademedia,
                    onClick = {

                        semprioridademedia = !semprioridademedia

                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = laranja,
                        selectedColor = laranja

                    )
                )


                RadioButton(
                    selected = semprioridadealta,
                    onClick = {

                        semprioridadealta = !semprioridadealta

                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = vermelho,
                        selectedColor = vermelho

                    )
                )

            }
            Botao(
                onClick = {

                    var mensagem = true

                  scope.launch(Dispatchers.IO){
                 if (titulotarefa.isEmpty()) {
                     mensagem = false
                 } else if (
                    titulotarefa.isNotEmpty() && descricaotarefa.isNotEmpty() && semprioridadebaixa) {
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEBAIXA)
                     mensagem = true
                 } else if (titulotarefa.isNotEmpty() && descricaotarefa.isNotEmpty() && semprioridademedia){
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEMEDIA)
                     mensagem = true

                 }else if (titulotarefa.isNotEmpty() && descricaotarefa.isNotEmpty() && semprioridadealta){
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEALTA)
                     mensagem = true

                 }else if (titulotarefa.isNotEmpty() && descricaotarefa.isNotEmpty() && semprioridadetarefa){
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.SEMPRIORIDADE)
                     mensagem = true

                 }else if (titulotarefa.isNotEmpty() && semprioridadebaixa){
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEBAIXA)
                     mensagem = true

                 } else if (titulotarefa.isNotEmpty() && semprioridadebaixa){
                    tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEBAIXA)
                    mensagem = true

                } else if (titulotarefa.isNotEmpty() && semprioridademedia){
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEMEDIA)
                     mensagem = true

                 } else if (titulotarefa.isNotEmpty() && semprioridadealta){
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.PRIORIDADEALTA)
                     mensagem = true

                 }else{
                     tarefasrepositorio.salvartarefa(titulotarefa,descricaotarefa,Constantes.SEMPRIORIDADE)
                     mensagem = true


                 }                 }
                    
                    scope.launch(Dispatchers.Main){
                        if (mensagem){
                            Toast.makeText(context, "Sucesso ao salvar atrefa", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                            
                        } else {
                            Toast.makeText(context, "Titulo da tarefa é obrigatorio", Toast.LENGTH_SHORT).show()

                        }
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp),
                texto = "Salvar"
            )

        }
    }

}




