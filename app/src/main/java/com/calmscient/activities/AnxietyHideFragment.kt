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

package com.calmscient.activities

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
import com.calmscient.adapters.AnxietyHideAdapterClass
import com.calmscient.databinding.LayoutAnxietyHideBinding
import com.calmscient.fragments.ManageAnxietyFragment

data class AnxietyHideDataClass(
    val text1: String?,
    val text2: String?,
    val text3: String?,
    val text4: String?,
    val imageanxiety: Int?,
    val text5: String?,
    val text6: String?,
    val text7: String?,
    var selectedOption: Int = -1
)

class AnxietyHideFragment : Fragment() {
    private lateinit var binding: LayoutAnxietyHideBinding
    private lateinit var anxietyadapter: AnxietyHideAdapterClass
    private val anxietyText = mutableListOf<AnxietyHideDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutAnxietyHideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerSnapHelper = PagerSnapHelper()
        //val customProgressDialog = CustomProgressDialog(requireContext())
        pagerSnapHelper.attachToRecyclerView(binding.anxietyHideRecyclerView)

        binding.previousQuestion.visibility = View.GONE
        val title = arguments?.getString("description")

        binding.tvTitle.text = title

        progressBar = view.findViewById(R.id.progressBar2)

        binding.progressBar2.progress =
            currentQuestionIndex * (maxProgress / (anxietyText.size - 1))
        setupNavigation()
        initializeAdapter()
        if (title == getString(R.string.anxiety_hide)) {
            displayAnxietyHideViews()
        }
        stepIndicators = listOf(
            view.findViewById(R.id.step1Indicator),
            view.findViewById(R.id.step2Indicator),
            view.findViewById(R.id.step3Indicator),
            view.findViewById(R.id.step4Indicator),
            view.findViewById(R.id.step5Indicator),
            view.findViewById(R.id.step6Indicator),
            view.findViewById(R.id.step7Indicator),
            view.findViewById(R.id.step8Indicator),
            view.findViewById(R.id.step9Indicator),
        )
        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            loadFragment(ManageAnxietyFragment())
        }
    }

    private fun initializeAdapter() {
        binding.anxietyHideRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        anxietyadapter = AnxietyHideAdapterClass(anxietyText)
        binding.anxietyHideRecyclerView.adapter = anxietyadapter
    }

    private fun displayAnxietyHideViews() {
        anxietyText.add(
            AnxietyHideDataClass(
                null,
                null,
                null,
                null,
                R.drawable.anxiety_hide_1,
                null,
                getString(R.string.anxiety_hideCard1_text1),
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                getString(R.string.anxiety_hideCard2_text1),
                getString(R.string.anxiety_hideCard2_text2),
                getString(R.string.anxiety_hideCard2_text3),
                null,
                R.drawable.anxiety_hide_2,
                getString(R.string.anxiety_hideCard2_text4),
                getString(R.string.anxiety_hideCard2_text5),
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                getString(R.string.anxiety_hideCard3_text1),
                null,
                null,
                null,
                R.drawable.anxiety_hide_3,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                getString(R.string.anxiety_hideCard4_text1),
                null,
                null,
                null,
                R.drawable.anxiety_hide_4,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                getString(R.string.anxiety_hideCard5_text1),
                null,
                null,
                getString(R.string.anxiety_hideCard5_text2),
                R.drawable.anxiety_hide_5,
                null,
                getString(R.string.anxiety_hideCard5_text3),
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                null,
                null,
                null,
                getString(R.string.anxiety_hideCard6_text1),
                R.drawable.anxiety_hide_6,
                null,
                getString(R.string.anxiety_hideCard6_text2),
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                null,
                null,
                null,
                null,
                R.drawable.anxiety_hide_7,
                getString(R.string.anxiety_hideCard7_text1),
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                getString(R.string.anxiety_hideCard8_text1),
                null,
                getString(R.string.anxiety_hideCard8_text2),
                null,
                R.drawable.anxiety_hide_8,
                null,
                null,
                null
            )
        );
        anxietyText.add(
            AnxietyHideDataClass(
                getString(R.string.anxiety_hideCard9_text1),
                null,
                null,
                null,
                R.drawable.whitebg,
                null,
                null,
                null
            )
        );
        /*anxietyText.add(
            AnxietyHideDataClass(
                null,
                null,
                getString(R.string.anxiety_card10_text1),
                null,
                null,
                R.drawable.anxiety10,
                null,
                getString(R.string.anxiety_card10_text2),
                null
            )
        );*/
        anxietyadapter.notifyDataSetChanged()
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.anxietyHideRecyclerView.addOnScrollListener(object :
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

                        progressBar.progress =
                            currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

                        updateStepIndicators()
                    }
                }
            }
        })
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
            binding.anxietyHideRecyclerView.smoothScrollToPosition(currentQuestionIndex)

            // Calculate and set the progress based on the current question index
            progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyText.size - 1))

            // Update the step indicators (ImageViews) as active or inactive
            updateStepIndicators()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}