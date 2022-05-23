package com.ankurjb.newsapp.latestnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LatestNewsViewModel(

) : ViewModel() {

    fun getTopNews() {
        viewModelScope.launch {

        }
    }
}