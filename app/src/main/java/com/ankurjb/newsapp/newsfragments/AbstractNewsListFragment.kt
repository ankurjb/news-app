package com.ankurjb.newsapp.newsfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ankurjb.newsapp.base.ViewBindingFragment
import com.ankurjb.newsapp.databinding.FragmentNewsListBinding
import com.ankurjb.newsapp.isGone
import com.ankurjb.newsapp.isVisible
import com.ankurjb.newsapp.model.Either
import com.ankurjb.newsapp.model.TopNews
import com.ankurjb.newsapp.newsfragments.adapter.NewsListAdapter
import com.ankurjb.newsapp.showMessage
import com.ankurjb.newsapp.topnews.TopNewsViewModel

class AbstractNewsListFragment : ViewBindingFragment<FragmentNewsListBinding>(
    FragmentNewsListBinding::inflate
) {

    private val viewModel: TopNewsViewModel by activityViewModels()

    private lateinit var adapter: NewsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsListAdapter {
            viewModel
        }
        binding.newsListRecyclerView.adapter = adapter

        viewModel.topNewsLiveData.observe(viewLifecycleOwner) { response ->
            response?.let { updateUI(it) }
        }

    }

    private fun updateUI(state: Either<TopNews>) {
        when (state) {
            is Either.Success -> {
                binding.progressCircular.isGone()
                adapter.apply {
                    articles = state.response.articles
                    notifyDataSetChanged()
                }
            }
            is Either.Error -> {
                binding.progressCircular.isGone()
                requireActivity().showMessage(state.errorMessage)
            }
            is Either.Loading -> {
                binding.progressCircular.isVisible()
            }
        }
    }

    companion object {
        const val TAG = "AbstractNewsListFragment"
        fun build() = AbstractNewsListFragment()
    }
}
