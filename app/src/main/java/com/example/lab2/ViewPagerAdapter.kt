package com.example.lab2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: ActivityFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyFragment()
            1 -> UsersFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}