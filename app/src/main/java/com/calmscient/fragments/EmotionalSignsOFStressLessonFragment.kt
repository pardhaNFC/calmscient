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

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.adapters.EmotionalSignsOFStressAdapter
import com.calmscient.databinding.FragmentEmotionalSignsOFStressLessonBinding
import com.calmscient.di.remote.EmotionalSignsOFStressDataClass

class EmotionalSignsOFStressLessonFragment : Fragment() {
    private lateinit var binding: FragmentEmotionalSignsOFStressLessonBinding
    private lateinit var anxietyAdapter: EmotionalSignsOFStressAdapter
    private val anxietyText = mutableListOf<EmotionalSignsOFStressDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmotionalSignsOFStressLessonBinding.inflate(inflater, container, false)
        val view = binding.root

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.emotionalSignStressRecylerView)

        binding.previousQuestion.visibility = View.GONE
        val title = arguments?.getString("description")

        binding.tvTitlePlayer.text = title

        // Find your ProgressBar by its ID
        progressBar = binding.anxietyAndDietProgressBar

        // Set the initial progress
        progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

        setupNavigation()
        initializeAdapter()

        displayMakeAPlanViews()

        stepIndicators = listOf(
            binding.step1Indicator,
            binding.step2Indicator,
            binding.step3Indicator,
            binding.step4Indicator,
            binding.step5Indicator,
            binding.step6Indicator
        )

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }

    private fun initializeAdapter() {
        binding.emotionalSignStressRecylerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        anxietyAdapter = EmotionalSignsOFStressAdapter(requireContext(),anxietyText)
        binding.emotionalSignStressRecylerView.adapter = anxietyAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayMakeAPlanViews() {
        anxietyText.add(
            EmotionalSignsOFStressDataClass(
                getString(R.string.emotional_stress_sign_card_1_desc_1),
                null,
                null,
                null,
                )
        );
        anxietyText.add(
            EmotionalSignsOFStressDataClass(
                getString(R.string.emotional_stress_sign_card_2_desc_1),
                null,
                null,
                null,
            )
        );
        anxietyText.add(
            EmotionalSignsOFStressDataClass(
                null,
                getString(R.string.fight),
                R.drawable.emotional_sign_stess_3,
                getString(R.string.emotional_stress_sign_card_3_desc_2),
            )
        );
        anxietyText.add(
            EmotionalSignsOFStressDataClass(
              null,
                getString(R.string.flight),
                R.drawable.emotional_sign_stess_4,
                getString(R.string.emotional_stress_sign_card_4_desc_2),
            )
        );
        anxietyText.add(
            EmotionalSignsOFStressDataClass(
                null,
                getString(R.string.freeze),
                R.drawable.emotional_sign_stess_5,
                getString(R.string.emotional_stress_sign_card_5_desc_2),
            )
        );
        anxietyText.add(
            EmotionalSignsOFStressDataClass(
                null,
                getString(R.string.shut_down),
                R.drawable.emotional_sign_stess_6,
                getString(R.string.emotional_stress_sign_card_6_desc_2),
            )
        );

        anxietyAdapter.notifyDataSetChanged()
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.emotionalSignStressRecylerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition != currentQuestionIndex) {
                        previousQuestionIndex = currentQuestionIndex
                        currentQuestionIndex = firstVisibleItemPosition

                        // Calculate and set the progress based on the current question index
                        progressBar.progress =
                            currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
                        // Update the step indicators (ImageViews) as active or inactive
                        updateStepIndicators()
                    }
                }
            }
        })
    }

    private fun updateStepIndicators() {

        // Update the current step indicator to active
        if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
            stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_activetickmark)
        }

        if (currentQuestionIndex == 0) {
            binding.previousQuestion.visibility = View.GONE
        } else {
            binding.previousQuestion.visibility = View.VISIBLE
        }

        if (currentQuestionIndex == stepIndicators.size - 1) {
            binding.nextQuestion.visibility = View.GONE
        } else {
            binding.nextQuestion.visibility = View.VISIBLE
        }
    }

    private fun navigateToQuestion(index: Int, isNext: Boolean) {
        if (index in 0 until anxietyText.size) {
            if (isNext) {
                previousQuestionIndex = currentQuestionIndex
            } else {
                // Update the current step indicator to inactive when going to the previous question
                if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
                    stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
                }
            }
            currentQuestionIndex = index
            binding.emotionalSignStressRecylerView.smoothScrollToPosition(currentQuestionIndex)
            // Calculate and set the progress based on the current question index
            progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
            // Update the step indicators (ImageViews) as active or inactive
            updateStepIndicators()
        }
    }

}
