package com.example.listadetarefas.view

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.listadetarefas.ui.theme.Purple40
import com.example.listadetarefas.ui.theme.White
import com.example.listadetarefas.ui.theme.azul
import com.example.listadetarefas.ui.theme.laranja
import com.example.listadetarefas.ui.theme.purple400
import com.example.listadetarefas.ui.theme.verde
import androidx.compose.foundation.layout.Column as Column1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    navController: NavController
) {
    val (lowPriorityChecked, setLowPriorityChecked) = remember { mutableStateOf(false) }
    val (mediumPriorityChecked, setMediumPriorityChecked) = remember { mutableStateOf(false) }
    val (highPriorityChecked, setHighPriorityChecked) = remember { mutableStateOf(false) }

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
                    checked = lowPriorityChecked,
                    onCheckedChange = { setLowPriorityChecked(it) },
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
                    onCheckedChange = { setMediumPriorityChecked(it) },
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
                    onCheckedChange = { setHighPriorityChecked(it) },
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
                            val selectedFilters = mutableListOf<Priority>()
                            if (lowPriorityChecked) selectedFilters.add(Priority.LOW)
                            if (mediumPriorityChecked) selectedFilters.add(Priority.MEDIUM)
                            if (highPriorityChecked) selectedFilters.add(Priority.HIGH)

                            navController.navigate("TaskList/${selectedFilters.joinToString { it.name }}") {
                                launchSingleTop = true
                                popUpTo("Filter") {
                                    inclusive = true

                                }
                            }

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
data class Task(val title: String, val description: String, val priority: Priority)

enum class Priority(val color: Color) {
    LOW(verde),
    MEDIUM(laranja),
    HIGH(verde)
}

val dummyTasks = listOf(
    Task("Task 1", "Description for Task 1", Priority.LOW),
    Task("Task 2", "Description for Task 2", Priority.MEDIUM),
    Task("Task 3", "Description for Task 3", Priority.HIGH),
    Task("Task 4", "Description for Task 4", Priority.MEDIUM),
    Task("Task 5", "Description for Task 5", Priority.LOW),
    Task("Task 6", "Description for Task 6", Priority.HIGH)
)