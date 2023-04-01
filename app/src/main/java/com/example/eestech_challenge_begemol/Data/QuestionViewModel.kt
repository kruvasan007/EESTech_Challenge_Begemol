package com.example.eestech_challenge_begemol.Data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eestech_challenge_begemol.Model.Question

class QuestionViewModel : ViewModel() {
    private val questionList = Repository.getQuestion(-1)

    fun getQuestionList(id: Int) = Repository.getQuestion(id)

    fun getCountQuestion() = questionList.value?.size
}