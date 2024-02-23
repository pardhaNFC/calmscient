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
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.adapters.AnxietyAndSleepAdapter
import com.calmscient.databinding.FragmentAnxietyAndSleepBinding
import com.calmscient.di.remote.AnxietyAndSleepDataClass

class AnxietyAndSleepFragment : Fragment() {
    private lateinit var binding: FragmentAnxietyAndSleepBinding
    private lateinit var anxietyAdapter: AnxietyAndSleepAdapter
    private val anxietyText = mutableListOf<AnxietyAndSleepDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar
    private lateinit var cardItemDataClass: AnxietyAndSleepDataClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnxietyAndSleepBinding.inflate(inflater, container, false)
        val view = binding.root

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.anxietyAndSleepRecylerView)

        binding.previousQuestion.visibility = View.GONE
        val title = arguments?.getString("description")

        binding.tvTitlePlayer.text = title

        // Find your ProgressBar by its ID
        progressBar = binding.anxietyAndSleepProgressBar

        // Set the initial progress
        progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

        setupNavigation()
        initializeAdapter()

        displayMakeAPlanViews()

        stepIndicators = listOf(
            binding.step1Indicator,
            binding.step2Indicator,
            binding.step3Indicator
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
        binding.anxietyAndSleepRecylerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        anxietyAdapter = AnxietyAndSleepAdapter(requireContext(),anxietyText)
        binding.anxietyAndSleepRecylerView.adapter = anxietyAdapter
    }

    private fun displayMakeAPlanViews() {
        anxietyText.add(
            AnxietyAndSleepDataClass(
                null,
                R.drawable.anxiety_and_sleep_1,
                null,
                getString(R.string.yes_we_ve_all_heard_it),
                getString(R.string.anxiety_and_sleep_card_1_desc_1),
                null


            )
        );
        anxietyText.add(
            AnxietyAndSleepDataClass(
                R.drawable.ic_bulb_recognize,
                R.drawable.anxiety_and_sleep_2,
                null,
                getString(R.string.sleep_deprivation_causes_insulin_resistance),
                getString(R.string.anxiety_and_sleep_card_2_desc_1),
                null
            )
        );
        anxietyText.add(
            AnxietyAndSleepDataClass(
                null,
                R.drawable.anxiety_and_sleep_3,
                getString(R.string.anxiety_and_sleep_card_3_desc_1),
                getString(R.string.here_are_some_tips_to_improve_your_sleep),
                getString(R.string.anxiety_and_sleep_card_3_desc_2),
                getString(R.string.anxiety_and_sleep_card_3_heading_2),



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

        binding.anxietyAndSleepRecylerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
            binding.anxietyAndSleepRecylerView.smoothScrollToPosition(currentQuestionIndex)
            // Calculate and set the progress based on the current question index
            progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
            // Update the step indicators (ImageViews) as active or inactive
            updateStepIndicators()
        }
    }

}
