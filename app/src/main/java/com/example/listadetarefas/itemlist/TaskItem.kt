package com.example.listadetarefas.itemlist

import android.app.AlertDialog
import android.content.Context
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
import com.example.listadetarefas.model.Task
import com.example.listadetarefas.repositorio.TaskRepository
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.black
import com.example.listadetarefas.ui.theme.laranja
import com.example.listadetarefas.ui.theme.shapeCard
import com.example.listadetarefas.ui.theme.verde
import com.example.listadetarefas.ui.theme.vermelho
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskItem(
  position: Int,
  taskList: MutableList<Task>,
  context: Context,
  navController: NavController,
  taskRepository: TaskRepository,
) {
  val title = taskList[position].title
  val description = taskList[position].description
  val priority = taskList[position].priority
  val scope = rememberCoroutineScope()


  fun deleteDialog() {
    val alertDialog = AlertDialog.Builder(context)
    alertDialog.setTitle("Deletar tarefa")
      .setMessage("Deseja excluir a tarefa?")
      .setPositiveButton("Sim") { _, _ ->
        taskRepository.deleteTask(title.toString())
        scope.launch(Dispatchers.Main) {
          taskRepository.deleteTask(title.toString())
          taskList.removeAt(position)
          navController.navigate("TaskList")
          Toast.makeText(context, "Tarefa deletada", Toast.LENGTH_SHORT).show()
        }
      }
      .setNegativeButton("Nao") { _, _ -> }
      .show()
  }

  val priorityLevel: String = when (priority) {
    0 -> "Sem Prioridade"
    1 -> "Prioridade Baixa"
    2 -> " Prioridade Media"
    else -> "Prioridade Alta"
  }

  val color = when (priority) {
    0 -> black
    1 -> verde
    2 -> laranja
    else -> vermelho
  }


  Card(
    colors = CardDefaults.cardColors(containerColor = White),
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 20.dp)
  )
  {
    ConstraintLayout(
      modifier = Modifier.padding(20.dp)
    ) {
      val (txtTitle, txtDescription, cardPriority, txtPriority, btDelete) = createRefs()

      Text(
        text = title.toString(),
        modifier = Modifier.constrainAs(txtTitle) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
        }
      )

      Text(text = description.toString(),
        modifier = Modifier.constrainAs(txtDescription) {
          top.linkTo(txtTitle.bottom, margin = 10.dp)
          start.linkTo(parent.start)
        }
      )

      Text(text = priorityLevel,
        modifier = Modifier.constrainAs(txtPriority) {
          top.linkTo(txtDescription.bottom, margin = 10.dp)
          start.linkTo(parent.start)
        }
      )

      Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
          .size(30.dp)
          .constrainAs(cardPriority) {
            top.linkTo(txtPriority.top)
            start.linkTo(txtPriority.end, margin = 10.dp)
            bottom.linkTo(txtPriority.bottom)
          }, shape = shapeCard.large
      ) {}

      IconButton(
        onClick = { deleteDialog() },
        modifier = Modifier
          .constrainAs(btDelete) {
            top.linkTo(txtPriority.top)
            start.linkTo(cardPriority.end, margin = 10.dp)
            bottom.linkTo(txtPriority.bottom)
          }
      ) {
        Image(
          imageVector = ImageVector.vectorResource(id = R.drawable.baseline_backspace_24),
          contentDescription = null
        )
      }
    }
  }
}

