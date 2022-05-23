package com.ankurjb.newsapp.newsfragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ankurjb.newsapp.base.ViewBindingFragment
import com.ankurjb.newsapp.databinding.FragmentNewsListBinding
import com.ankurjb.newsapp.latestnews.LatestNewsViewModel

class AbstractNewsListFragment : ViewBindingFragment<FragmentNewsListBinding>(
    FragmentNewsListBinding::inflate
) {

    val viewModel: LatestNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        fun build() = AbstractNewsListFragment()
    }
}
