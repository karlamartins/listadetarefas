package com.example.listadetarefas.repositorio

import com.example.listadetarefas.datasource.Datasource
import com.example.listadetarefas.model.Tarefas
import kotlinx.coroutines.flow.Flow

class Tarefasrepositorio {


    private val datasource = Datasource()

    fun salvartarefa(tarefas:String, descriçao:String, prioridade:Int){

        datasource.salvartarefa(tarefas,descriçao,prioridade)

    }

    fun recuperartarefas(): Flow<MutableList<Tarefas>> {
        return datasource.recuperartarefas()

    }
 fun deletartarefa(tarefas:String){

     datasource.deletartarefa(tarefas)
 }



}