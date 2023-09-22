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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.adapters.AnxietyQuestionsAdapter
import com.calmscient.databinding.ActivityMakeAplanBinding
import com.calmscient.di.remote.CardItemDataClass

class MakeAPlanFragment : Fragment() {
    private lateinit var binding: ActivityMakeAplanBinding
    private lateinit var anxietyadapter: AnxietyQuestionsAdapter
    private val anxietyText = mutableListOf<AnxietyTextDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar
    private lateinit var cardItemDataClass: CardItemDataClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityMakeAplanBinding.inflate(inflater, container, false)
        val view = binding.root

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.optionsRecyclerView1)

        binding.previousQuestion.visibility = View.GONE
        val title = arguments?.getString("description")

        binding.tvTitlePlayer.text = title

        // Find your ProgressBar by its ID
        progressBar = binding.progressBar2

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
            binding.step6Indicator,
            binding.step7Indicator,
            binding.step8Indicator,
            binding.step9Indicator
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
        binding.optionsRecyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        anxietyadapter = AnxietyQuestionsAdapter(anxietyText)
        binding.optionsRecyclerView1.adapter = anxietyadapter
    }

    private fun displayMakeAPlanViews() {
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                getString(R.string.make_plan_card1_text1),
                null,
                null,
                null,
                R.drawable.plan1,
                null,
                getString(R.string.make_plan_card1_text2),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                null,
                null,
                null,
                R.drawable.plan2,
                null,
                getString(R.string.make_plan_card2_text1),
                getString(R.string.make_plan_card2_text2),
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card3_text1),
                getString(R.string.make_plan_card3_text2),
                null,
                R.drawable.plan3,
                null,
                getString(R.string.make_plan_card3_text3),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card4_text1),
                getString(R.string.make_plan_card4_text2),
                null,
                R.drawable.plan4,
                null,
                getString(R.string.make_plan_card4_text3),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card5_text1),
                getString(R.string.make_plan_card5_text2),
                null,
                R.drawable.plan5,
                null,
                getString(R.string.make_plan_card5_text3),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card6_text1),
                getString(R.string.make_plan_card6_text2),
                null,
                R.drawable.plan6,
                null,
                getString(R.string.make_plan_card6_text3),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card7_text1),
                getString(R.string.make_plan_card7_text2),
                null,
                R.drawable.plan7,
                null,
                getString(R.string.make_plan_card7_text3),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card8_text1),
                getString(R.string.make_plan_card8_text2),
                null,
                R.drawable.plan8,
                null,
                getString(R.string.make_plan_card8_text3),
                null,
            )
        );
        anxietyText.add(
            AnxietyTextDataClass(
                null,
                null,
                getString(R.string.make_plan_card9_text1),
                getString(R.string.make_plan_card9_text2) ,
                null,
                R.drawable.plan9,
                null,
                getString(R.string.make_plan_card9_text3),
                null,
            )
        );

        anxietyadapter.notifyDataSetChanged()
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.optionsRecyclerView1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
            binding.optionsRecyclerView1.smoothScrollToPosition(currentQuestionIndex)
            // Calculate and set the progress based on the current question index
            progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
            // Update the step indicators (ImageViews) as active or inactive
            updateStepIndicators()
        }
    }
}
