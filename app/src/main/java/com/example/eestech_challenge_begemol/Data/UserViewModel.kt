package com.example.eestech_challenge_begemol.Data

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private var user = Repository.getUser()

    fun getUser() = user
}