package com.example.listadetarefas.datasource

import com.example.listadetarefas.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {
  private val db = FirebaseFirestore.getInstance()
  private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
  private val allTasks: StateFlow<MutableList<Task>> = _allTasks

  fun saveTask(task: Task) {
    val taskMap = hashMapOf(
      "title" to task.title,
      "description" to task.description,
      "priority" to task.priority
    )
    task.title?.let { nonNullTitle ->
      db.collection("tasks")
        .document(nonNullTitle)
        .set(taskMap)
    }
  }

  fun recoverTask(): Flow<MutableList<Task>> {
    val taskList: MutableList<Task> = mutableListOf()

    db.collection("tasks").get().addOnCompleteListener { querySnapshot ->
      if (querySnapshot.isSuccessful) {
        for (document in querySnapshot.result) {
          val task = document.toObject(Task::class.java)
          taskList.add(task)
          _allTasks.value = taskList
        }
      }
    }
    return allTasks
  }

  fun deleteTask(title: String) {
    db.collection("tasks")
      .document(title)
      .delete()
  }
}