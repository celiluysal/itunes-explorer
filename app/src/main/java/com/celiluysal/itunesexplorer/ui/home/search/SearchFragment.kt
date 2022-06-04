package com.celiluysal.itunesexplorer.ui.home.search

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.celiluysal.itunesexplorer.databinding.FragmentSearchBinding
import com.celiluysal.itunesexplorer.ui.base.BaseFragment
import com.celiluysal.itunesexplorer.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun loadUI() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment())
        }, 1500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}