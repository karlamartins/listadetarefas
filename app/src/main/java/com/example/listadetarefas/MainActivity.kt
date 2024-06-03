package com.example.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadetarefas.ui.theme.ListaDeTarefasTheme
import com.example.listadetarefas.view.Listatarefas
import com.example.listadetarefas.view.Salvar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeTarefasTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "Listatarefas") {

                    composable(
                        route = "Listatarefas"
                    ){
                        Listatarefas(navController)
                    }
                    composable (
                        route = "Salvar"
                    ){
                        Salvar(navController)
                    }


                }
            }
        }
    }
}



