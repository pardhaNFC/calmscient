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
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.databinding.LayoutPostponeworryfirstscreenBinding

class PostponeWorryFirstScreen : Fragment() {
    private lateinit var binding: LayoutPostponeworryfirstscreenBinding
    private var currentIndex = 0
    private lateinit var anxietyadapter: AnxietyQuestionsAdapter
    private lateinit var progressBar: ProgressBar
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private val anxietyText = mutableListOf<AnxietyTextDataClass>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutPostponeworryfirstscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            loadFragment(ManageAnxietyFragment())
        }
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            //loadFragment(AnxietyPostponeWorry())
            fragmentManager?.let { it1 ->
                replaceFragmentWithPlayerFragment(
                    it1,
                    null,
                    null,
                    null,
                    requireActivity().getString(R.string.postpone_worry),
                    null,
                    null,
                    AnxietyPostponeWorry()
                    //AnxietyPostponeWorry()
                )
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun replaceFragmentWithPlayerFragment(
        fragmentManager: FragmentManager,
        videoResourceId: String?,
        audioResourceId:String?,
        heading: String?,
        description :String?,
        summary: String?,
        dialogText: String?,
        fragment : Fragment
    ) {
        val args = Bundle()
        args.putString("audioResourceId", audioResourceId)
        args.putString("videoResourceId", videoResourceId)
        args.putString("heading", heading)
        args.putString("description", description)
        args.putString("summary", summary)
        args.putString("dialogText", dialogText)
        fragment.arguments = args

        fragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}