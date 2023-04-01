package com.example.eestech_challenge_begemol.Data

import androidx.lifecycle.MutableLiveData
import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.Model.Question
import com.example.eestech_challenge_begemol.Model.Task
import com.example.eestech_challenge_begemol.Model.User
import com.example.eestech_challenge_begemol.Retrofit.Common
import kotlinx.coroutines.*

object Repository {
    private val retrofitService = Common.retrofitService
    private var job: Job? = null
    private val taskList = MutableLiveData<List<Task>>()
    private val categoryList = MutableLiveData<List<Category>>()
    private val questionList = MutableLiveData<List<Question>>()
    private val user = MutableLiveData<User>()
    private val loadError = MutableLiveData<String?>()
    private val loading = MutableLiveData<Boolean>()
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        loadUser(0)
        loadCategories()
    }

    private fun loadUser(id: Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitService.getUserById(id.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    user.value = response.body()
                    loadError.value = null
                    loading.value = false
                }
            }
        }
        loadError.value = ""
        loading.value = false
    }

    private fun loadTask(id: Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitService.getTask(id.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    taskList.value = response.body()
                    loadError.value = null
                    loading.value = false
                }
            }
        }
        loadError.value = ""
        loading.value = false
    }

    private fun loadCategories() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = retrofitService.getCategories()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    categoryList.value = response.body()
                    loadError.value = null
                    loading.value = false
                }
            }
        }
        loadError.value = ""
        loading.value = false
    }

    private fun loadQuestion(id: Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = retrofitService.getQuestions(id.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    println(response.body().toString())
                    questionList.value = response.body()
                    loadError.value = null
                    loading.value = false
                }
            }
        }
        loadError.value = ""
        loading.value = false
    }

    fun getQuestion(id: Int): MutableLiveData<List<Question>> {
        if (id != -1) loadQuestion(id)
        return questionList
    }

    private fun refresh() {
        loadCategories()
        loadUser(0)
    }

    fun getCategory(): MutableLiveData<List<Category>> {
        if (job == null) refresh()
        return categoryList
    }

    fun getUser(): MutableLiveData<User> {
        if (job == null) refresh()
        return user
    }

    fun getTask(id: Int): MutableLiveData<List<Task>> {
        if (id != -1) loadTask(id)
        return taskList
    }
}