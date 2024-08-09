package com.example.listadetarefas.model

import java.util.UUID


data class Task (
    val title : String ? = null,
    val description : String? = null,
    val priority : Int? = null,
    val id: String = UUID.randomUUID().toString(),
)
