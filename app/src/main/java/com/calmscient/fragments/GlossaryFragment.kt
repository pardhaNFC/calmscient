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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.GlossaryAdapter
import com.calmscient.di.remote.Task
import com.calmscient.databinding.LayoutGlossaryBinding

class GlossaryFragment : Fragment() {
    private lateinit var binding: LayoutGlossaryBinding
    private val glossaryAdapter = GlossaryAdapter(mutableListOf())
    private val taskWorkData = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutGlossaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }

        addTaskData()

        binding.glossaryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.glossaryRecyclerView.adapter = glossaryAdapter
        glossaryAdapter.updateTasks(taskWorkData)
    }

    private fun addTaskData() {
        val tasks = listOf(
            Task(
                "A",
                getString(R.string.adrenaline),
                getString(R.string.adrenaline_summary)
            ),
            Task(
                "A",
                getString(R.string.anxiety_attack),
                getString(R.string.anxiety_attack_summary)
            ),
            Task(
                "A",
                getString(R.string.automatic_thinking),
                getString(R.string.automatic_thinking_summary)
            ),

            Task(
                "B",
                getString(R.string.biased_thinking),
                getString(R.string.biased_thinking_summary)
            ),
            Task(
                "C",
                getString(R.string.cortisol),
                getString(R.string.cortisol_summary)
            ),
            Task(
                "C",
                getString(R.string.cognitive_distortion),
                getString(R.string.cognitive_distortion_summary),
            ),
            Task(
                "C",
                getString(R.string.competency),
                getString(R.string.competency_summary)
            ),
            Task(
                "C",
                getString(R.string.compulsive_behaviour),
                getString(R.string.compulsive_behaviour_summary),
            ),
            Task(
                "D",
                getString(R.string.Dependency),
                getString(R.string.Dependency_summary)
            ),
            Task(
                "G",
                getString(R.string.gaba),
                getString(R.string.gaba_summary)
            ),
            Task(
                "H",
                getString(R.string.hyperventilation),
                getString(R.string.hyper_summary),
            ),
            Task(
                "M",
                getString(R.string.mindful_breathing),
                getString(R.string.mindful_breathing_summary)
            ),
            Task(
                "O",
                getString(R.string.obsession),
                getString(R.string.obsession_summary)
            ),
            Task(
                "P",
                getString(R.string.panic_attack),
                getString(R.string.panic_attack_summary),
            ),
            Task(
                "P",
                getString(R.string.progressive_muscle_relaxation),
                getString(R.string.progressive_muscle_relaxation_summary),

                ),
            Task(
                "R",
                getString(R.string.resilience),
                getString(R.string.resilience_summary),
            ),
            Task(
                "R",
                getString(R.string.rigid_thinking),
                getString(R.string.rigid_thinking_summary),
            ),
            Task(
                "R",
                getString(R.string.rumination),
                getString(R.string.rumination_summary),
            ),
            Task(
                "S",
                getString(R.string.selfcompassion),
                getString(R.string.selfcompassion_summary),
            ),
            Task(
                "S",
                getString(R.string.serotonin),
                getString(R.string.serotonin_summary),
            ),
            Task(
                "W",
                getString(R.string.window_of_tolerance),
                getString(R.string.window_of_tolerance_summary),
            ),
        )
        taskWorkData.addAll(tasks)
        glossaryAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        binding.glossaryRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.glossaryRecyclerView.adapter = glossaryAdapter
        glossaryAdapter.updateTasks(taskWorkData)
        addTaskData()
        super.onStart()
    }
}
