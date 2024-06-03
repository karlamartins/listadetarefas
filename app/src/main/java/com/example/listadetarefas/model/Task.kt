package com.example.listadetarefas.model

data class Task (
    val title : String ? = null,
    val description : String? = null,
    val priority : Int? = null
)