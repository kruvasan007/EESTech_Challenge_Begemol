package com.example.eestech_challenge_begemol.Model

data class Question(
    var id: Int? = null,
    var text: String? = null,
    var answers: List<Answer>
)

data class Answer(
    var id: Int? = null,
    var text: String? = null
)
