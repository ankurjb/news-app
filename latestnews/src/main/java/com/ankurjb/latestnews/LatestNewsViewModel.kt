package com.ankurjb.latestnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankurjb.latestnews.repo.LatestNewsRepository
import com.ankurjb.core.model.Article
import com.ankurjb.core.model.Either
import com.ankurjb.core.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LatestNewsViewModel @Inject constructor(
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
