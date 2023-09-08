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
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.HandoverYourHeartExercisesBinding

class HandOverYourHeartFragment : Fragment() {
    private lateinit var binding: HandoverYourHeartExercisesBinding
    private var isFavorite = true
    private lateinit var favoritesIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(ExerciseFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HandoverYourHeartExercisesBinding.inflate(inflater, container, false)
        val favoritesIcon = binding.favoritesIcon
        favoritesIcon.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                favoritesIcon.setImageResource(R.drawable.mindfullexercise_heart__image) // Set your desired color
            } else {
                favoritesIcon.setImageResource(R.drawable.heart_icon_fav) // Reset color
            }
        }
        binding.menuicon.setOnClickListener {
            loadFragment(ExerciseFragment())
        }
        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
