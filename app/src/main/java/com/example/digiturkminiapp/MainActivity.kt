package com.example.digiturkminiapp

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.digiturkminiapp.core.BaseActivity

class MainActivity : BaseActivity() {

    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val host = NavHostFragment.create(R.navigation.nav_host)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, host).setPrimaryNavigationFragment(host).commit()
    }
}
