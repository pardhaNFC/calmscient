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
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val intent = Intent(activity, MindfulnessExercisesActivity::class.java)
            startActivity(intent)
        }
        binding.handOverYourHeartCard.setOnClickListener {
            val intent = Intent(activity, HandOverYourHeartActivity::class.java)
            startActivity(intent)
        }
        binding.butterflyCard.setOnClickListener {
            val intent = Intent(activity, ButterflyHugExercisesActivity::class.java)
            startActivity(intent)
        }
        binding.dancingExercisesCard.setOnClickListener {
            val  intent = Intent(activity, DancingExercisesActivity::class.java)
            startActivity(intent)
        }
        binding.runningExerciseCard.setOnClickListener {
            val intent = Intent(activity, RunningExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.bodyMovementExerciseCard.setOnClickListener {
            val intent = Intent(activity, BodyMovementExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.deepBreathingCard.setOnClickListener {
            val intent = Intent(activity, DeepBreathingExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.mindfulnessExerciseCard.setOnClickListener {
            val intent = Intent(activity, MindfulnessExercisesActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}