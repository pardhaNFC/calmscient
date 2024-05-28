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
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.LayoutResultsBinding


class ResultsFragment : Fragment() {
    lateinit var binding: LayoutResultsBinding
    companion object {
        const val SOURCE_SCREEN_KEY = "source_screen"
        const val SCREENINGS_FRAGMENT = 0
        const val YOUR_STRESS_FRAGMENT = 1
        // Add more constants if needed for other fragments
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            val sourceFragment = arguments?.getInt(SOURCE_SCREEN_KEY)
            when (sourceFragment) {
                SCREENINGS_FRAGMENT -> loadFragment(ScreeningsFragment())
                YOUR_STRESS_FRAGMENT -> loadFragment(YourStressTriggersQuizFragment())
                // Handle other fragments if needed
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutResultsBinding.inflate(inflater, container, false)
        binding.resultsBackIcon.setOnClickListener {
            val sourceFragment = arguments?.getInt(SOURCE_SCREEN_KEY)
            when (sourceFragment) {
                SCREENINGS_FRAGMENT -> loadFragment(ScreeningsFragment())
                YOUR_STRESS_FRAGMENT -> loadFragment(YourStressTriggersQuizFragment())
                // Handle other fragments if needed
            }
        }
        binding.needToTalkWithSomeOneResults.setOnClickListener {
            loadFragment(EmergencyResourceFragment())
        }
//        binding.torchResults.setOnClickListener {
//            val dialogFragment = ResultsinfoPopupFragment()
//            dialogFragment.show(requireActivity().supportFragmentManager, "ResultsInfoDialog")
//        }

        val sourceFragment = arguments?.getInt(SOURCE_SCREEN_KEY)
        if (sourceFragment == YOUR_STRESS_FRAGMENT) {
            // Display info icon
            binding.infoIcon.visibility = View.VISIBLE
            binding.torchResults.visibility = View.GONE
            binding.torchResults.setOnClickListener {
                // Add logic to handle info icon click
                // For example, show an info dialog
            }
        } else {
            // Display torch icon
            // binding.torchResults.setImageResource(R.drawable.ic_bulb_recognize)
            binding.torchResults.visibility = View.VISIBLE
            binding.infoIcon.visibility = View.GONE
            binding.torchResults.setOnClickListener {
                val dialogFragment = ResultsinfoPopupFragment()
                dialogFragment.show(requireActivity().supportFragmentManager, "ResultsInfoDialog")
            }
        }

        val description = arguments?.getString("title")
        description?.let {
            binding.titleTextView.text = it
        }
        resultPercent()
        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {

        val bundle = Bundle()
        bundle.putString("description", getString(R.string.work_related_stress_quiz))
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(com.calmscient.R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun resultPercent() {
        binding.progressbarResult.setMax(100);
        binding.progressbarResult.setProgress(60);

        val progressBar: ProgressBar
        val textView: TextView
        var progressStatus = 0
        val handler = Handler()

        Thread {
            while (progressStatus < 60) {
                progressStatus += 1
                handler.post(Runnable {
                    binding.progressbarResult.progress = progressStatus
                })
                try {
                    Thread.sleep(25)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}