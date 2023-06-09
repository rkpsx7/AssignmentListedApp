package com.dev_akash.assignmentlistedapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev_akash.assignmentlistedapp.ui.fragents.RecentLinksFragment
import com.dev_akash.assignmentlistedapp.ui.fragents.TopLinksFragment

class LinksTabAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fm, lifecycle) {

    private val topLinksFragment = TopLinksFragment()
    private val recentLinksFragment = RecentLinksFragment()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> topLinksFragment
            else -> recentLinksFragment
        }
    }
}