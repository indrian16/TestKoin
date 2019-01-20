package io.indrian16.testkoin.view.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pageList = arrayListOf<Page>()

    override fun getItem(position: Int): Fragment = pageList[position].fragment

    override fun getCount(): Int = pageList.size

    override fun getPageTitle(position: Int): CharSequence? = pageList[position].titlePage

    fun addPage(fragment: Fragment, pageTitle: String) {

        pageList.add(Page(fragment, pageTitle))
        notifyDataSetChanged()
    }

    data class Page(

            val fragment: Fragment,
            val titlePage: String
    )
}