package com.ankurjb.newsapp.latestnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.newsapp.latestnews.network.LatestNewsRepository
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News
import kotlinx.coroutines.launch

class LatestNewsViewModel(
    private val latestNewsRepository: LatestNewsRepository
) : ViewModel() {

    private val _latestNewsLiveData = MutableLiveData<Either<News>>()
    internal val latestNewsLiveData: LiveData<Either<News>> = _latestNewsLiveData

    private val _newsDetailsLiveData = MutableLiveData<Article>()
    internal val newsDetailsLiveData: LiveData<Article> = _newsDetailsLiveData

    fun getLatestNews() {
        viewModelScope.launch {
            _latestNewsLiveData.value = Either.Loading("")
            _latestNewsLiveData.value = latestNewsRepository.getLatestNews()
        }
    }

    fun updateNewsDetails(article: Article) {
        _newsDetailsLiveData.value = article
    }
}