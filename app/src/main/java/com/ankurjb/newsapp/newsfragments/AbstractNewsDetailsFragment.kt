package com.ankurjb.newsapp.newsfragments

import android.os.Bundle
import android.view.View
import com.ankurjb.newsapp.base.ViewBindingFragment
import com.ankurjb.newsapp.databinding.FragmentNewsDetailsBinding

class AbstractNewsDetailsFragment : ViewBindingFragment<FragmentNewsDetailsBinding>(
    FragmentNewsDetailsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun build() = AbstractNewsListFragment()
    }
}
