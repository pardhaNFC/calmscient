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
import com.calmscient.fragments.DiscoveryFragment
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
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        bottomNav.itemIconTintList = null
        // Set the HomeFragment icon to the new icon as default
        bottomNav.menu.findItem(R.id.home).setIcon(R.drawable.home_selected)
        loadFragment(HomeFragment())
        bottomNav.setOnItemSelectedListener {
            bottomNav.menu.findItem(R.id.home).setIcon(R.drawable.homenew)
            bottomNav.menu.findItem(R.id.discovery).setIcon(R.drawable.discoverynew)
            bottomNav.menu.findItem(R.id.exercises).setIcon(R.drawable.exercisesnew)
            bottomNav.menu.findItem(R.id.rewards).setIcon(R.drawable.rewardsnew)
            when (it.itemId) {
                R.id.home -> {
                    it.setIcon(R.drawable.home_selected)
                    loadFragment(HomeFragment())
                    true
                }

                R.id.discovery -> {
                    //Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_SHORT).show()
                    loadFragment(DiscoveryFragment())
                    it.setIcon(R.drawable.dis_selected)
                    true
                }

                R.id.exercises -> {
                    // loadFragment(ExerciseFragment())
                    Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_SHORT).show()
                    it.setIcon(R.drawable.exercises_selected)
                    true
                }

                R.id.rewards -> {
                    //loadFragment(RewardsFragment())
                    Toast.makeText(applicationContext, "Coming Soon", Toast.LENGTH_SHORT).show()
                    it.setIcon(R.drawable.rewards_selected)
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