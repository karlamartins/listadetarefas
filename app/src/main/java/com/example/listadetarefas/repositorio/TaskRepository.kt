package com.example.listadetarefas.repositorio

import com.example.listadetarefas.constant.Constants.ALL_PRIORITY
import com.example.listadetarefas.constant.Constants.HIGH_PRIORITY
import com.example.listadetarefas.constant.Constants.LOW_PRIORITY
import com.example.listadetarefas.constant.Constants.MEDIUM_PRIORITY
import com.example.listadetarefas.constant.Constants.NO_PRIORITY
import com.example.listadetarefas.datasource.DataSource
import com.example.listadetarefas.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TaskRepository {

  private val datasource = DataSource()
  var filters = mutableListOf(NO_PRIORITY, LOW_PRIORITY, MEDIUM_PRIORITY, HIGH_PRIORITY, ALL_PRIORITY)

  suspend fun saveTask(task: Task) {
    datasource.saveTask(task)
  }
  suspend fun updateTask(updatedTask: Task) {
    withContext(Dispatchers.IO) {
      datasource.updateTask(updatedTask)
    }
  }
  suspend fun getTaskById(id: String): Task? = withContext(Dispatchers.IO) {
    datasource.getTaskById(id)
  }

  fun recoverTask(): Flow<List<Task>> {
    return datasource.recoverTask()
      .map { taskList ->
        taskList.filter { filters.contains(it.priority) }
      }
  }

  fun deleteTask(title: String) {
    datasource.deleteTask(title)
  }

  fun setFilter(selectedFilters: MutableList<Int>) {
    filters = selectedFilters
  }
}

