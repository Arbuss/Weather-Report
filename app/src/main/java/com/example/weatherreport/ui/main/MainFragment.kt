package com.example.weatherreport.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherreport.App
import com.example.weatherreport.R
import com.example.weatherreport.ui.list.ListFragment
import com.example.weatherreport.ui.map.MapFragment
import com.example.weatherreport.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment(R.layout.fragment_main) {
    private val fragments = arrayOf(
        SearchFragment(),
        ListFragment(),
        MapFragment()
    )

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabs()
    }

    private fun initViewPager() {
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = fragments.size

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }

        viewPager.adapter = adapter
    }

    private fun initTabs() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = requireContext().resources.getStringArray(R.array.tabs).getOrElse(position) { "" }
        }.attach()
    }
}