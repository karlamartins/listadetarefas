package com.example.listadetarefas.datasource

import com.example.listadetarefas.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class DataSource {
  private val db = FirebaseFirestore.getInstance()
  private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
  private val allTasks: StateFlow<MutableList<Task>> = _allTasks

  fun saveTask(task: Task) {
    val taskMap = hashMapOf(
      "title" to task.title,
      "description" to task.description,
      "priority" to task.priority,
      "id" to task.id
    )
      db.collection("tasks")
        .document(task.id)
        .set(taskMap)
    }
  suspend fun updateTask(task: Task) {
    val taskMap = hashMapOf(
      "title" to task.title,
      "description" to task.description,
      "priority" to task.priority,
      "id" to task.id
    )

    val docRef = db.collection("tasks").document(task.id)
    docRef.update(taskMap as Map<String, Any>).await()
  }

  suspend fun getTaskById(id: String): Task? {
    return try {
      val docRef = db.collection("tasks").document(id)
      val snapshot = docRef.get().await()
      if (snapshot.exists()) {
        snapshot.toObject(Task::class.java)
      } else {
        null
      }
    } catch (e: Exception) {
      null
    }
  }
  fun recoverTask(): Flow<MutableList<Task>> {
    val taskList: MutableList<Task> = mutableListOf()

    db.collection("tasks").get().addOnCompleteListener { querySnapshot ->
      if (querySnapshot.isSuccessful) {
        for (taskDocument in querySnapshot.result) {
          val task = taskDocument.toObject(Task::class.java)
          taskList.add(task)
        }
        _allTasks.value = taskList
      }
    }
    return allTasks
  }

  fun deleteTask(id: String) {
    db.collection("tasks")
      .document(id)
      .delete()
  }

}


