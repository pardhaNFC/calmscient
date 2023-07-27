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


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R

import com.calmscient.activities.SettingsActivity
import com.calmscient.adapters.VideoAdapter
import com.calmscient.activities.WeeklySummary
import com.calmscient.Interface.CellClickListener
import com.calmscient.activities.CalendarViewActivity

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var profileImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val videoResourceIds = listOf(
            R.raw.video1,
            R.raw.video2,
            R.raw.video2,
            R.raw.video1,
            R.raw.video3,
            R.raw.video1,
            R.raw.video3,
            R.raw.video2,
            R.raw.video1,
            R.raw.video3
        )

        recyclerView = rootView.findViewById(R.id.recyclerViewVideos)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        videoAdapter = VideoAdapter(videoResourceIds)
        recyclerView.adapter = videoAdapter
        // Find the myMedicalRecordsLayout
        val myMedicalRecordsLayout = rootView.findViewById<View>(R.id.myMedicalRecordsLayout)
        profileImage = rootView.findViewById(R.id.img_profile1)
        profileImage.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
        myMedicalRecordsLayout.setOnClickListener {
            loadFragment(MedicationsFragment())
            /*val intent = Intent(activity, CalendarViewActivity::class.java)
            startActivity(intent)*/
        }
        val weeklySummaryLayout = rootView.findViewById<View>(R.id.weeklySummaryLayout)

        weeklySummaryLayout.setOnClickListener {
            openWeeklySummaryActivity()
        }
        return rootView
    }

    private fun loadFragment(fragment: Fragment) {
        /* fragmentManager?.beginTransaction()?.apply {
             replace(R.id.flFragment, fragment)
                 .addToBackStack(null)
                 .commit()
         }*/
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }

    private fun openWeeklySummaryActivity() {
        val intent = Intent(activity, WeeklySummary::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}