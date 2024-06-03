package com.example.listadetarefas.datasource

import com.example.listadetarefas.model.Tarefas
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Datasource {

    private val db = FirebaseFirestore.getInstance()

    private val _todasastarefas = MutableStateFlow<MutableList<Tarefas>>(mutableListOf())
    private val todasastarefas: StateFlow<MutableList<Tarefas>> = _todasastarefas


    fun salvartarefa(tarefas:String, descriçao:String, prioridade:Int){

        val tarefamap = hashMapOf(
            "tarefas" to tarefas,
            "descriçao" to descriçao,
            "prioridade" to prioridade
        )

        db.collection("tarefas").document(tarefas).set(tarefamap).addOnCompleteListener {

        }.addOnFailureListener {

        }

    }
    fun recuperartarefas(): Flow<MutableList<Tarefas>>{

        val listatarefas: MutableList<Tarefas>
= mutableListOf()

        db.collection("tarefas").get().addOnCompleteListener {
            querySnapshot -> if (querySnapshot.isSuccessful){
                for ( documento in querySnapshot.result){
               val tarefas = documento.toObject(Tarefas::class.java)
                listatarefas.add(tarefas)
                    _todasastarefas.value = listatarefas
                }
        }
        }
        return todasastarefas
    }

    fun deletartarefa(tarefas: String){

        db.collection("tarefas").document(tarefas).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }

    }


}