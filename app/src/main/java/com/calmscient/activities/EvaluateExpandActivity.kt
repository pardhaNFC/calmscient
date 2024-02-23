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

import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.ExpandFiveRestructureAdapter
import com.calmscient.databinding.LayoutRestructureFiveExpandBinding
import com.calmscient.di.remote.Task

class EvaluateExpandActivity : AppCompat() {
    private lateinit var reviewExpandAdapter: ExpandFiveRestructureAdapter
    lateinit var binding: LayoutRestructureFiveExpandBinding
    private val taskWorkData = mutableListOf<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutRestructureFiveExpandBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        initializeView()
        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initializeView() {
        binding.expandRestructureRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        reviewExpandAdapter = ExpandFiveRestructureAdapter(taskWorkData)
        addTaskData()
        binding.expandRestructureRecyclerView.adapter = reviewExpandAdapter
    }

    private fun addTaskData() {
        val tasks = listOf(
            Task(
                "A",
                getString(R.string.all_summary_title),
                getString(R.string.all_summary)
            ),
            Task(
                "C",
                getString(R.string.anxiety_biased_card_catas),
                getString(R.string.Catastrophizing_summary)
            ),
            Task(
                "E",
                getString(R.string.anxiety_biased_card_emotional),
                getString(R.string.Emotional_summary),
            ),
            Task(
                "J",
                getString(R.string.anxiety_biased_card_jumping),
                getString(R.string.Jumping_summary)
            ),
            Task(
                "M",
                getString(R.string.anxiety_biased_card_making),
                getString(R.string.Making_summary)
            ),
            Task(
                "N",
                getString(R.string.anxiety_biased_card_negative),
                getString(R.string.Negative_summary)
            ),
            Task(
                "P",
                getString(R.string.anxiety_biased_card_perfect),
                getString(R.string.Perfectionist_summary)
            ),

            Task(
                "P",
                getString(R.string.anxiety_biased_card_personalizeBlame),
                getString(R.string.Personalization_summary)
            ),
        )
        taskWorkData.addAll(tasks)
        reviewExpandAdapter.notifyDataSetChanged()
    }
}