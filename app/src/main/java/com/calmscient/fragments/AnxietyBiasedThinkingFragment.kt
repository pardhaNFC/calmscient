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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.adapters.BiasedThinkingAdapter
import com.calmscient.databinding.LayoutBiasedThinkingBinding
import com.calmscient.di.remote.AnxietyBiasedDataClass

class AnxietyBiasedThinkingFragment : Fragment() {
    private lateinit var binding: LayoutBiasedThinkingBinding
    private lateinit var biasedAdapter: BiasedThinkingAdapter
    private val anxietyBiasedText = mutableListOf<AnxietyBiasedDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private val maxProgress = 99
    private lateinit var stepIndicators: List<ImageView>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutBiasedThinkingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("description")
        binding.tvTitle.text = title
        stepIndicators = listOf(
            view.findViewById(R.id.step1Indicator),
            view.findViewById(R.id.step2Indicator),
            view.findViewById(R.id.step3Indicator),
            view.findViewById(R.id.step4Indicator),
            view.findViewById(R.id.step5Indicator),
            view.findViewById(R.id.step6Indicator),
            view.findViewById(R.id.step7Indicator),
            view.findViewById(R.id.step8Indicator)
        )
        //setupNavigation()
        initializeAdapter()
        if (title == getString(R.string.biased_think)) {
            displayBiasedViews()
        }
        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            loadFragment(ManageAnxietyFragment())
        }
        binding.backIcon.setOnClickListener {
            binding.layoutBack.visibility = View.GONE
            binding.idToolbar.visibility = View.VISIBLE
            binding.previousQuestion.visibility = View.VISIBLE
            binding.scrollBiased.visibility = View.VISIBLE
            binding.layoutBiasedRecyclerview.visibility = View.GONE
        }
        binding.btnAll.setOnClickListener {
            backNavigation()
            scrollToPosition(0)
        }
        binding.btnCatas.setOnClickListener {
            backNavigation()
            scrollToPosition(1)
        }
        binding.btnPerfect.setOnClickListener {
            backNavigation()
            scrollToPosition(2)
        }
        binding.btnNegative.setOnClickListener {
            backNavigation()
            scrollToPosition(3)
        }
        binding.btnPersonalize.setOnClickListener {
            backNavigation()
            scrollToPosition(4)
        }
        binding.btnJumping.setOnClickListener {
            backNavigation()
            scrollToPosition(5)
        }
        binding.btnMaking.setOnClickListener {
            backNavigation()
            scrollToPosition(6)
        }
        binding.btnEmotional.setOnClickListener {
            backNavigation()
            scrollToPosition(7)
        }
    }

    fun backNavigation() {
        binding.layoutBack.visibility = View.VISIBLE
        binding.idToolbar.visibility = View.GONE
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
            //if (index == currentQuestionIndex) {
            binding.previousQuestion.visibility = View.VISIBLE
            binding.scrollBiased.visibility = View.VISIBLE
            binding.layoutBiasedRecyclerview.visibility = View.GONE
            //}
        }

        binding.recyclerViewBiased.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition != currentQuestionIndex) {
                        previousQuestionIndex = currentQuestionIndex
                        currentQuestionIndex = firstVisibleItemPosition

                        binding.biasedProgressBar.progress =
                            currentQuestionIndex * (maxProgress / (anxietyBiasedText.size - 1))

                        updateStepIndicators()
                    }
                }
            }
        })
    }

    private fun navigateToQuestion(index: Int, isNext: Boolean) {
        if (index in 0 until anxietyBiasedText.size) {
            if (isNext) {
                previousQuestionIndex = currentQuestionIndex
            } else {
                // Update the current step indicator to inactive when going to the previous question
                if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
                    stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
                }
            }
            currentQuestionIndex = index
            binding.recyclerViewBiased.smoothScrollToPosition(currentQuestionIndex)

            // Calculate and set the progress based on the current question index
            binding.biasedProgressBar.progress =
                currentQuestionIndex * (maxProgress / (anxietyBiasedText.size - 1))

            // Update the step indicators (ImageViews) as active or inactive
            updateStepIndicators()

        }
    }

    private fun updateStepIndicators() {
        // Update the previous step indicator to inactive
        /*if (previousQuestionIndex >= 0 && previousQuestionIndex < stepIndicators.size) {
            stepIndicators[previousQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
        }*/
        // Update the current step indicator to active
        if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
            stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_activetickmark)
        }
        if (currentQuestionIndex == 0) {
            binding.previousQuestion.visibility = View.VISIBLE
        } else {
            binding.previousQuestion.visibility = View.VISIBLE
        }
        if (currentQuestionIndex == stepIndicators.size - 1) {
            binding.nextQuestion.visibility = View.GONE
        } else {
            binding.nextQuestion.visibility = View.VISIBLE
        }

    }

    private fun scrollToPosition(position: Int) {
        binding.scrollBiased.visibility = View.GONE
        binding.layoutBiasedRecyclerview.visibility = View.VISIBLE
        binding.recyclerViewBiased.scrollToPosition(position)
    }

    private fun displayBiasedViews() {
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_all),
                R.drawable.biased_all,
                getString(R.string.anxiety_biased_card_one),
                null,
                0
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_catas),
                R.drawable.biased_catas,
                getString(R.string.anxiety_biased_card_two),
                null,
                1
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_perfect),
                R.drawable.biased_perfect,
                getString(R.string.anxiety_biased_card_three),
                null,
                2
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_negative),
                R.drawable.biased_negative,
                getString(R.string.anxiety_biased_card_four),
                null,
                3
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_personalizeBlame),
                R.drawable.biased_personal,
                getString(R.string.anxiety_biased_card_five),
                null,
                4
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_jumping),
                R.drawable.biased_jumping,
                getString(R.string.anxiety_biased_card_six),
                null,
                5
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_making),
                R.drawable.biased_making,
                getString(R.string.anxiety_biased_card_seven),
                null,
                6
            )
        )
        anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_emotional),
                R.drawable.biased_making,
                getString(R.string.anxiety_biased_card_eight),
                null,
                7
            )
        )
        /*anxietyBiasedText.add(
            AnxietyBiasedDataClass(
                getString(R.string.anxiety_biased_card_emotional),
                R.drawable.biased_emotion,
                getString(R.string.anxiety_biased_card_eight),
                getString(R.string.anxiety_biased_card_eight_one),
                7
            )
        )*/
        biasedAdapter.notifyDataSetChanged()
    }

    private fun initializeAdapter() {
        binding.recyclerViewBiased.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        /*binding.recyclerViewBiased.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean = false
        }*/
        biasedAdapter = BiasedThinkingAdapter(anxietyBiasedText)
        binding.recyclerViewBiased.adapter = biasedAdapter
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}