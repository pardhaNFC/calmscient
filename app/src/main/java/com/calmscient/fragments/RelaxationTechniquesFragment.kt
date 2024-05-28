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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.calmscient.R
import com.calmscient.activities.DashboardActivity
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.FragmentCopyingWithStressBinding
import com.calmscient.databinding.FragmentCostBenefitAnalysisBinding
import com.calmscient.databinding.FragmentDefenseResponseBinding
import com.calmscient.databinding.FragmentManagingStressToReduceAnxietyBinding
import com.calmscient.databinding.FragmentRelaxationTechniquesBinding
import com.calmscient.databinding.FragmentStressHormonesBinding

interface ExerciseInteractionListener {
    fun onExerciseSelected()
}
class RelaxationTechniquesFragment : Fragment() {
    private lateinit var binding: FragmentRelaxationTechniquesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelaxationTechniquesBinding.inflate(inflater, container, false)
        val view = binding.root

        val title = arguments?.getString("description")

        binding.tvTitlePlayer.text = title

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.goToExerciseLayout.setOnClickListener{
            navigateToExerciseFragment()
        }
        return view

    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun navigateToExerciseFragment() {
        val dashboardActivity = activity as? DashboardActivity
        dashboardActivity?.onExerciseSelected()
        loadFragment(ExerciseFragment())
    }




}