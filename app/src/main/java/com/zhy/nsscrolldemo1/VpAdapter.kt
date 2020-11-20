package com.zhy.nsscrolldemo1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class VpAdapter(val list: List<Fragment>, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(
        fragmentManager,
        FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    override fun getItem(position: Int): Fragment = list[position]

    override fun getCount(): Int = list.size
    override fun getPageTitle(position: Int): CharSequence? {
        return "Demo$position"
    }
}