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
import com.calmscient.adapters.CalmingTheBodyAdapter
import com.calmscient.databinding.LayoutCalmingBodyBinding
import com.calmscient.di.remote.CardItemDataClass

data class AnxietyCalmingDataClass(
    val text1: String?,
    val text2: String?,
    val text2_1: String?,
    val imageanxiety: Int?,
    val text3_1: String?,
    val text3: String?,
    val anxietybulb: Int?,
    val text5: String?,
    var selectedOption: Int = -1
)

class AnxietyCalmingBodyFragment : Fragment() {
    private lateinit var binding: LayoutCalmingBodyBinding
    lateinit var calmingAdapter: CalmingTheBodyAdapter
    private val anxietyCalmingText = mutableListOf<AnxietyCalmingDataClass>()
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private lateinit var stepIndicators: List<ImageView>
    private val maxProgress = 99
    private lateinit var progressBar: ProgressBar
    private lateinit var cardItemDataClass: CardItemDataClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutCalmingBodyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.calmingBodyRecyclerView)

        binding.previousQuestion.visibility = View.GONE
        val title = arguments?.getString("description")

        binding.tvTitle.text = title

        progressBar = view.findViewById(R.id.calmingProgressBar)

        progressBar.progress = currentQuestionIndex * (maxProgress / (anxietyCalmingText.size - 1))


        setupNavigation()
        initializeAdapter()
        if (title == getString(R.string.calming_body)) {
            displayCalmingViews()
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
            view.findViewById(R.id.step10Indicator)
        )

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            loadFragment(ManageAnxietyFragment())
        }
    }

    private fun initializeAdapter() {
        binding.calmingBodyRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        calmingAdapter = CalmingTheBodyAdapter(requireContext(), anxietyCalmingText)
        binding.calmingBodyRecyclerView.adapter = calmingAdapter
    }

    private fun displayCalmingViews() {
        anxietyCalmingText.add(
            AnxietyCalmingDataClass(
                getString(R.string.anxiety_calming_title_one),
                getString(R.string.anxiety_calming_card_one),
                null,
                R.drawable.img_calming_1,
                null,
                getString(R.string.anxiety_calming_card_two),
                null,
                null
            )
        );
        anxietyCalmingText.add(
            AnxietyCalmingDataClass(
                null,
                null,
                null,
                R.drawable.img_calming_2,
                null,
                getString(R.string.anxiety_calming_card_two1),
                null,
                null
            )
        );
        anxietyCalmingText.add(
            AnxietyCalmingDataClass(
                null,
                null,
                null,
                R.drawable.img_calming_3,
                null,
                getString(R.string.anxiety_calming_card_three),
                null,
                null
            )
        );
        anxietyCalmingText.add(
            AnxietyCalmingDataClass(
                null,
                null,
                getString(R.string.anxiety_calming_title_two),
                R.drawable.img_calming_4,
                getString(R.string.anxiety_calming_title_two),
                null,
                null,
                null
            )
        );

        anxietyCalmingText.add(
            AnxietyCalmingDataClass(
                null,
                null,
                getString(R.string.anxiety_calming_title_two),
                R.drawable.img_calming_4,
                getString(R.string.anxiety_calming_title_two),
                null,
                null,
                null
            )
        );
        calmingAdapter.notifyDataSetChanged()
    }

    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.calmingBodyRecyclerView.addOnScrollListener(object :
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
                            currentQuestionIndex * (maxProgress / (anxietyCalmingText.size - 1))

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
        if (index in 0 until anxietyCalmingText.size) {
            if (isNext) {
                previousQuestionIndex = currentQuestionIndex
            } else {
                // Update the current step indicator to inactive when going to the previous question
                if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
                    stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
                }
            }
            currentQuestionIndex = index
            binding.calmingBodyRecyclerView.smoothScrollToPosition(currentQuestionIndex)

            // Calculate and set the progress based on the current question index
            progressBar.progress =
                currentQuestionIndex * (maxProgress / (anxietyCalmingText.size - 1))

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