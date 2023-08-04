/*
 *
 *      Copyright (c) 2023- NFC Solutions, - All Rights Reserved
 *      All source code contained herein remains the property of NFC Solutions Incorporated
 *      and protected by trade secret or copyright law of USA.
 *      Dissemination, De-compilation, Modification and Distribution are strictly prohibited unless
 *      there is a prior written permission or license agreement from NFC Solutions.
 *
 *      Author : @Pardha Saradhi
 */

package com.calmscient.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class DashboardActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        bottomNav.itemIconTintList = null
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    it.setIcon(R.drawable.homen)

                    loadFragment(HomeFragment())
                    true
                }

                R.id.discovery -> {
                    //loadFragment(DiscoveryFragment())
                    Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_LONG).show()
                    it.setIcon(R.drawable.discoveryn)

                    true
                }

                R.id.exercises -> {
                    // loadFragment(ExerciseFragment())
                    Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_LONG).show()
                    it.setIcon(R.drawable.exercises)

                    true
                }

                R.id.rewards -> {
                    //loadFragment(RewardsFragment())
                    Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_LONG).show()
                    it.setIcon(R.drawable.rewards)

                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }
}