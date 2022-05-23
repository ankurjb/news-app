package com.ankurjb.newsapp.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.News
import com.ankurjb.newsapp.topnews.network.TopNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TopNewsViewModel @Inject constructor(
    private val topNewsRepository: TopNewsRepository
) : ViewModel() {

    private val _topNewsLiveData = MutableLiveData<Either<News>>()
    internal val topNewsLiveData: LiveData<Either<News>> = _topNewsLiveData

    private val _newsDetailsLiveData = MutableLiveData<Article>()
    internal val newsDetailsLiveData: LiveData<Article> = _newsDetailsLiveData

    fun getTopNews() {
        viewModelScope.launch {
            _topNewsLiveData.value = Either.Loading("")
            _topNewsLiveData.value = topNewsRepository.getTopNews()
        }
    }

    fun updateNewsDetails(article: Article) {
        _newsDetailsLiveData.value = article
    }

    companion object {
        const val TAG = "TOP_NEW_VIEW_MODEL_TAG"
    }
}
