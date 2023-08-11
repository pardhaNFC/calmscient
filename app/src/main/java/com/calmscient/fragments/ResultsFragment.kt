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
import androidx.fragment.app.Fragment
import com.calmscient.databinding.FragmentResultsLayoutBinding


class ResultsFragment : Fragment() {
    lateinit var binding: FragmentResultsLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsLayoutBinding.inflate(inflater, container, false)
        binding.resultsBackIcon.setOnClickListener {
            loadFragment(MedicalRecordsFragment())
        }
        binding.needToTalkWithSomeOneResults.setOnClickListener {
            loadFragment(EmergencyResourceFragment())
        }
        resultPercent()
        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(com.calmscient.R.id.flFragment, fragment)
        transaction.commit()
    }

    private fun resultPercent() {
        binding.progressbarResult.setMax(100);
        binding.progressbarResult.setProgress(60);
        /* //val percent_rises:Double = risesValue as Double
         val percentFormatter: NumberFormat
         val percentRises: String
         val percentRises_value: String

         percentFormatter = NumberFormat.getPercentInstance()
         *//*percentRises = percentFormatter.format(percent_rises)
        percentRises_value = (percent_rises * 100).toString()*//*
        //tv_rises_percent.text = "+"+percentRises_value+"%"
        //val temp = percentRises.removeSuffix("%").toInt()

        *//* progressRises.max = 10
         val currentProgress = 6
         ObjectAnimator.ofInt(progressRises,"start",currentProgress)
             .setDuration(2000).start()*//*
        //binding.progressbarResult.setProgress(temp)*/
        val progressBar: ProgressBar
        val textView: TextView
        var progressStatus = 0
        val handler = Handler()

        Thread {
            while (progressStatus < 60) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                handler.post(Runnable {
                    binding.progressbarResult.progress = progressStatus
                })
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(25)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}