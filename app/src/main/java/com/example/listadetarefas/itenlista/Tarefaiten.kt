package com.example.listadetarefas.itenlista

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listadetarefas.R
import com.example.listadetarefas.model.Tarefas
import com.example.listadetarefas.repositorio.Tarefasrepositorio
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.black
import com.example.listadetarefas.ui.theme.laranja
import com.example.listadetarefas.ui.theme.shapecard
import com.example.listadetarefas.ui.theme.verde
import com.example.listadetarefas.ui.theme.vermelho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Tarefaiten(
    position: Int,
    listatarefas: MutableList<Tarefas>,
    context: android.content.Context,
    navController: NavController

){


    val titulotarefa = listatarefas[position].tarefas
    val descriçao = listatarefas[position].descriçao
    val prioridade = listatarefas[position].prioridade


    val scope = rememberCoroutineScope()
    val tarefasrepositorio = Tarefasrepositorio()

    fun dialogDeleta(){

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar tarefa")
            .setMessage("Deseja excluir a tarefa?")
            .setPositiveButton("Sim"){_,_->

                tarefasrepositorio.deletartarefa(titulotarefa.toString())
                scope.launch(Dispatchers.Main){
                    listatarefas.removeAt(position)
                    navController.navigate("Listatarefas")
                    Toast.makeText(context,"Tarefa deletada", Toast.LENGTH_SHORT).show()
                }

            }
            .setNegativeButton("Nao"){_,_->}
            .show()
    }

    var nivelprioridade: String = when(prioridade){
        0 -> {
            "Sem prioridade"
        }
        1 -> {
            "prioridade baixa"
        }
        2 -> {
            " prioridade media"
        }
        else -> {
            "prioridade alta"
        }

    }

    val color = when(prioridade){
        0 -> {
            black
        }
        1 -> {
            verde
        }
        2 -> {
            laranja
        }
        else -> {
            vermelho
        }
    }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = White
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)


    )
    {

        ConstraintLayout (
            modifier = Modifier
                .padding(20.dp)
        ){
            val (txttitulo,txtdescriçao,cardpriodidade,txtprioridade,btdeletar) = createRefs()

            Text(text = titulotarefa.toString(),
                modifier = Modifier.constrainAs(txttitulo){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)

                }
            )


            Text(text = descriçao.toString(),
                modifier = Modifier.constrainAs(txtdescriçao){
                    top.linkTo(txttitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)

                }
            )


            Text(text = nivelprioridade,
                modifier = Modifier.constrainAs(txtprioridade){
                    top.linkTo(txtdescriçao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)

                }
            )
            Card(

                colors = CardDefaults.cardColors(
                    containerColor = color
                )
                , modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardpriodidade) {
                        top.linkTo(txtdescriçao.bottom, margin = 10.dp)
                        start.linkTo(txtprioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)


                    }, shape = shapecard.large

            ) {

            }

            IconButton(
                onClick = {
                    dialogDeleta()

                },
                modifier = Modifier
                    .constrainAs(btdeletar){
                        top.linkTo(txtdescriçao.bottom, margin = 10.dp)
                        start.linkTo(cardpriodidade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }
            ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_backspace_24), contentDescription = null)

            }

        }

    }

}

