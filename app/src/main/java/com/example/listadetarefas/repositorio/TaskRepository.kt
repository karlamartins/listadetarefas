package com.example.listadetarefas.repositorio

import com.example.listadetarefas.constant.Constants.HIGH_PRIORITY
import com.example.listadetarefas.constant.Constants.LOW_PRIORITY
import com.example.listadetarefas.constant.Constants.MEDIUM_PRIORITY
import com.example.listadetarefas.constant.Constants.NO_PRIORITY
import com.example.listadetarefas.datasource.DataSource
import com.example.listadetarefas.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository {

  private val datasource = DataSource()
  var filters = mutableListOf(NO_PRIORITY, LOW_PRIORITY, MEDIUM_PRIORITY, HIGH_PRIORITY)

  fun saveTask(task: Task) {
    datasource.saveTask(task)
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