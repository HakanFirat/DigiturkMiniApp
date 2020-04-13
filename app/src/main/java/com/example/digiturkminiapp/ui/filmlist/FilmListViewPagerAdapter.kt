package com.example.digiturkminiapp.ui.filmlist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.digiturkminiapp.model.Category

class FilmListViewPagerAdapter(fm:FragmentManager,private val categoryList: List<Category>):
        FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return MovieFragment(categoryList[position])
    }

    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return  categoryList[position].name
    }


}