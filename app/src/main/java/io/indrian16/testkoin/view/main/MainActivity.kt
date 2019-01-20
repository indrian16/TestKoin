package io.indrian16.testkoin.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.indrian16.testkoin.R
import io.indrian16.testkoin.view.local.LocalFragment
import io.indrian16.testkoin.view.main.adapter.ViewPagerAdapter
import io.indrian16.testkoin.view.remote.RemoteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar).apply {

            title = getString(R.string.app_name)
        }
        setupPager()
    }

    private fun setupPager() {

        val mAdapter = ViewPagerAdapter(supportFragmentManager)
        mAdapter.addPage(RemoteFragment.newInstance(), getString(R.string.remote))
        mAdapter.addPage(LocalFragment.newInstance(), getString(R.string.local))

        viewPager.adapter = mAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
