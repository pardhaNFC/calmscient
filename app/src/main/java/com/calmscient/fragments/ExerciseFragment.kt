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


import android.content.DialogInterface
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.calmscient.R
import com.calmscient.activities.BodyMovementExerciseActivity
import com.calmscient.activities.ButterflyHugExercisesActivity
import com.calmscient.activities.DancingExercisesActivity
import com.calmscient.activities.DeepBreathingExerciseActivity
import com.calmscient.activities.HandOverYourHeartActivity
import com.calmscient.activities.MindfulnessExercisesActivity
import com.calmscient.activities.RunningExerciseActivity
import com.calmscient.databinding.FragmentExerciseBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class ExerciseFragment:Fragment() {
    private lateinit var binding:FragmentExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        binding.mindfulnessExerciseCard.setOnClickListener {
            // Toast.makeText(requireActivity(), "Coming Soon", Toast.LENGTH_SHORT).show()
            loadFragment(MindfulnessExercisesFragment())
        }
        binding.handOverYourHeartCard.setOnClickListener {
            loadFragment(HandOverYourHeartFragment())
        }
        binding.butterflyCard.setOnClickListener {
            loadFragment(ButterflyHugExercisesFragment())
        }
        binding.dancingExercisesCard.setOnClickListener {
            loadFragment(DancingExercisesFragment())
        }
        binding.runningExerciseCard.setOnClickListener {
            loadFragment(RunningExerciseFragment())
        }
        binding.bodyMovementExerciseCard.setOnClickListener {
            loadFragment(BodyMovementExerciseFragment())
        }
        binding.deepBreathingCard.setOnClickListener {
            loadFragment(DeepBreathingExerciseFragment())
        }
        binding.walkingCard.setOnClickListener {
            loadFragment(MindfulWalkingExerciseFragment())
        }
        binding.muscleRelaxationCard.setOnClickListener {
            loadFragment(MuscleRelaxationExerciseFragment())
        }
        return binding.root
    }
    fun onBackPressed() {
        showExitConfirmationDialog()
    }
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.plz_confirm))
        builder.setMessage(getString(R.string.exit_app))
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            // User clicked "Yes," so exit the app
            requireActivity().finishAffinity() // This closes the entire app
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ ->
            // User clicked "No," so dismiss the dialog and stay on the current page
        }
        builder.setOnCancelListener(DialogInterface.OnCancelListener {
            // User canceled the dialog, do nothing
        })
        builder.show()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}