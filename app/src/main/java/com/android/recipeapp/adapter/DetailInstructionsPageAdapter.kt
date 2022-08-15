package com.android.submission2.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.recipeapp.R
import com.android.recipeapp.model.Meals
import com.android.recipeapp.ui.ingredients.IngredientsFragment
import com.android.recipeapp.ui.instruction.InstructionsFragment

class DetailInstructionsPageAdapter(
    fragment: Fragment,
    data: Bundle
) :
    FragmentStateAdapter(fragment) {
    private var fragmentBundle: Bundle = data
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = IngredientsFragment()
            1 -> fragment = InstructionsFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    companion object {
        val TAB_TITLES = listOf(
            R.string.ingrediens,
            R.string.intructions
        )
    }
}