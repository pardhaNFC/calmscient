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
import com.calmscient.adapters.AnxietyPostponeAdapter
import com.calmscient.databinding.LayoutpostponeworryBinding
import com.calmscient.di.remote.PostponeWorryDataClass

data class AnxietyPostPoneDataClass(
    val text1: String?,
    val text2: String?,
    val text3: String?,
    val text4: String?,
    val anxietybulb: Int?,
    val imageanxiety: Int?,
    val text5: String?,
    val text6: String?,
    val text7: String?,
    var selectedOption: Int = -1
)

class AnxietyPostponeWorry : Fragment() {
    private lateinit var binding: LayoutpostponeworryBinding
    private var currentIndex = 0
    private lateinit var anxietyadapter: AnxietyPostponeAdapter
    private lateinit var progressBar: ProgressBar
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private val anxietyText = mutableListOf<PostponeWorryDataClass>()
    /*private val textArrays = listOf(
        arrayOf(null, R.string.anxiety_postPoneCard0_text1, R.string.anxiety_postPoneCard0_text2),
        arrayOf(
            R.string.anxiety_postPoneCard1_text1,
            R.string.anxiety_postPoneCard1_text2,
            R.string.anxiety_postPoneCard1_text3
        ),
        arrayOf(R.string.anxiety_postPoneCard2_text1, R.string.anxiety_postPoneCard2_text2),
        arrayOf(
            R.string.anxiety_postPoneCard3_text1,
            R.string.anxiety_postPoneCard3_text2,
            R.string.anxiety_postPoneCard3_text3
        ),
        arrayOf(R.string.anxiety_postPoneCard4_text1, R.string.anxiety_postPoneCard4_text2)
    )*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutpostponeworryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.anxietyPostponeRecyclerView)
        binding.previousQuestion.visibility = View.GONE
        val title = arguments?.getString("description")
        //progressBar = view.findViewById(R.id.postponeProgressBar)
        binding.postponeProgressBar.progress =
            currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
        stepIndicators = listOf(
            view.findViewById(R.id.step1Indicator),
            view.findViewById(R.id.step2Indicator),
            view.findViewById(R.id.step3Indicator),
            view.findViewById(R.id.step4Indicator),
            view.findViewById(R.id.step5Indicator),
            view.findViewById(R.id.step6Indicator)
        )
        setupNavigation()
        initializeAdapter()
        if (title == getString(R.string.postpone_worry)) {
            displayAnxietyPostponeViews()
        }

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            loadFragment(ManageAnxietyFragment())
        }
    }

    private fun displayAnxietyPostponeViews() {
        anxietyText.add(
            PostponeWorryDataClass(
                AnxietyPostponeAdapter.VIEW_TYPE_TYPE_A,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            PostponeWorryDataClass(
                AnxietyPostponeAdapter.VIEW_TYPE_TYPE_B,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            PostponeWorryDataClass(
                AnxietyPostponeAdapter.VIEW_TYPE_TYPE_C,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            PostponeWorryDataClass(
                AnxietyPostponeAdapter.VIEW_TYPE_TYPE_C_A,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            PostponeWorryDataClass(
                AnxietyPostponeAdapter.VIEW_TYPE_TYPE_D,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            PostponeWorryDataClass(
                AnxietyPostponeAdapter.VIEW_TYPE_TYPE_D_A,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        );

        anxietyadapter.notifyDataSetChanged()
    }

    private fun initializeAdapter() {
        binding.anxietyPostponeRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        anxietyadapter = AnxietyPostponeAdapter(anxietyText)
        /*binding.anxietyPostponeRecyclerView.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }*/
        binding.anxietyPostponeRecyclerView.adapter = anxietyadapter
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.anxietyPostponeRecyclerView.addOnScrollListener(object :
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

                        binding.postponeProgressBar.progress =
                            currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

                        updateStepIndicators()
                    }
                }
            }
        })
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
            binding.anxietyPostponeRecyclerView.smoothScrollToPosition(currentQuestionIndex)

            // Calculate and set the progress based on the current question index
            binding.postponeProgressBar.progress =
                currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

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

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}