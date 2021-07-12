package com.example.gathr.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gathr.fragments.BulletinFragment
import com.example.gathr.fragments.CallFragment
import com.example.gathr.R
import com.example.gathr.fragments.ChatFragment
import com.example.gathr.fragments.ReminderFragment

//names of the tab layout
private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4
)

//switched between the sections on swipe or click on tabs
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        lateinit var fragment: Fragment
        when(position){
            0 -> fragment = CallFragment()
            1 -> fragment = ChatFragment()
            2 -> fragment = BulletinFragment()
            3 -> fragment = ReminderFragment()
        }
        return fragment

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 4 total pages.
        return 4
    }
}