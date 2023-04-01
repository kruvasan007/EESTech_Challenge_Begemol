package com.example.eestech_challenge_begemol.Data

import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {
    private var categoryList = Repository.getCategory()

    fun getCharacterList() = categoryList

    fun getCountCharacter() = categoryList.value?.size
}