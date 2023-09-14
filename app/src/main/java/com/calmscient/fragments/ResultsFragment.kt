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
import com.calmscient.databinding.LayoutResultsBinding


class ResultsFragment : Fragment() {
    lateinit var binding: LayoutResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(ScreeningsFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutResultsBinding.inflate(inflater, container, false)
        binding.resultsBackIcon.setOnClickListener {
            loadFragment(ScreeningsFragment())
        }
        binding.needToTalkWithSomeOneResults.setOnClickListener {
            loadFragment(EmergencyResourceFragment())
        }
        binding.torchResults.setOnClickListener {
            val dialogFragment = ResultsinfoPopupFragment()
            dialogFragment.show(requireActivity().supportFragmentManager, "ResultsInfoDialog")
        }
        resultPercent()
        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {
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