package com.example.listadetarefas.repositorio

import com.example.listadetarefas.datasource.DataSource
import com.example.listadetarefas.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository {

  private val datasource = DataSource()

  fun saveTask(task: Task) {
    datasource.saveTask(task)
  }

  fun recoverTask(): Flow<MutableList<Task>> {
    return datasource.recoverTask()
  }

  fun deleteTask(title: String) {
    datasource.deleteTask(title)
  }
}