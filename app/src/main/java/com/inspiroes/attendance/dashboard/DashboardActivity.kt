package com.inspiroes.attendance.dashboard

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.inspiroes.attendance.R
import com.inspiroes.attendance.dashboard.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_add_member -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_scan_qr -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_members_list ->{
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val homeFragment=HomeFragment()
        replaceFragment(homeFragment)
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.base_layout,fragment)
                .commit()
    }
}
