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

package com.calmscient.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.WeeklysummaryBinding
import com.google.android.material.snackbar.Snackbar

class WeeklySummaryFragment : Fragment() {
    private lateinit var binding:WeeklysummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(HomeFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeeklysummaryBinding.inflate(inflater, container, false)
        binding.moodCard.setOnClickListener {
            //loadFragment(SummaryofMoodFragment())
            snackBar()
        }
        binding.summarySleepCard.setOnClickListener {
            //loadFragment(SummaryofSleepFragment())
            snackBar()
        }
        binding.summaryPHQCard.setOnClickListener {
            //loadFragment(SummaryofPHQ9Fragment())
            snackBar()
        }
        binding.summaryOfGADCard.setOnClickListener {
            //loadFragment(SummaryofGAD7Fragment())
            snackBar()
        }
        binding.summaryOfAuditCard.setOnClickListener {
            //loadFragment(SummaryOfAUDITFragment())
            snackBar()
        }
        binding.summaryOfDASTCard.setOnClickListener {
            //loadFragment(SummaryOfDASTFragment())
            snackBar()
        }
        binding.summaryOfProgressWorkCard.setOnClickListener {
            //loadFragment(ProgressOnCourseWorkFragment())
            snackBar()
        }
        binding.backIcon.setOnClickListener {
            loadFragment(HomeFragment())
        }
        return binding.root
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun snackBar(){
        view?.let {
            Snackbar.make(it, getString(R.string.coming_soon), Snackbar.LENGTH_SHORT)
                .setAction("OK") {
                    // Code to execute when the action button is clicked
                }
                .show()
        }
    }
}