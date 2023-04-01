package com.example.eestech_challenge_begemol.Data

import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private var taskList = Repository.getTask(-1)

    fun getTaskList(id: Int) = Repository.getTask(id)
}