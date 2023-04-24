package com.dev_akash.assignmentlistedapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_akash.assignmentlistedapp.databinding.FragmentRecentLinksBinding

class RecentLinksFragment : Fragment(R.layout.fragment_recent_links) {

    private lateinit var binding: FragmentRecentLinksBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val linksViewAdapter = LinksViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recent_links, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.recentLinksLiveData.observe(viewLifecycleOwner){
            linksViewAdapter.submitList(it)
        }

    }

    private fun setRecyclerView() {
        binding.rvRecentLinks.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = linksViewAdapter
        }
    }
}