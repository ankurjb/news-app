package com.ankurjb.newsapp.topnews

import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ankurjb.newsapp.TopNewsRepository
import com.ankurjb.newsapp.model.Article
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.TopNews
import kotlinx.coroutines.launch

class TopNewsViewModel(
    private val topNewsRepository: TopNewsRepository
) : ViewModel() {

    private val _topNewsLiveData = MutableLiveData<Either<TopNews>>()
    internal val topNewsLiveData: LiveData<Either<TopNews>> = _topNewsLiveData

    private val _newsDetailsLiveData = MutableLiveData<Article>()
    internal val newsDetailsLiveData: LiveData<Article> = _newsDetailsLiveData

    fun getTopNews() {
        viewModelScope.launch {
            _topNewsLiveData.value = Either.Loading("")
            Log.d(TAG, "${_topNewsLiveData.value}")
            _topNewsLiveData.value = topNewsRepository.getTopNews()
            Log.d(TAG, "${_topNewsLiveData.value}")
        }
    }

    fun updateNewsDetails(article: Article) {
        _newsDetailsLiveData.value = article
        Log.d(TAG, "updateNewsDetails: ${newsDetailsLiveData.value}")
    }

    companion object {
        const val TAG = "TOP_NEW_VIEW_MODEL_TAG"
    }
}

inline fun <reified T : ViewModel> FragmentActivity.viewModelsFactory(crossinline viewModelInitialization: () -> T): Lazy<T> {
    return viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInitialization.invoke() as T
            }
        }
    }
}
