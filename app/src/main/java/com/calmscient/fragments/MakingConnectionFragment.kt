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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.adapters.MakingConnectionAdapter
import com.calmscient.databinding.FragmentMakingConnectionBinding
import com.calmscient.di.remote.MakingConnectionDataClass

class MakingConnectionFragment : Fragment() {
    private lateinit var binding: FragmentMakingConnectionBinding
    lateinit var restructureAdapter: MakingConnectionAdapter
    private var currentQuestionIndex = 0
    private var previousQuestionIndex = -1
    private val maxProgress = 99
    private lateinit var stepIndicators: List<ImageView>
    private val restructureText = mutableListOf<MakingConnectionDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakingConnectionBinding.inflate(inflater, container, false)
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
            view.findViewById(R.id.step8Indicator),
            view.findViewById(R.id.step9Indicator),
            view.findViewById(R.id.step10Indicator)
        )
        setupNavigation()
        initializeAdapter()
        if (title == getString(R.string.making_connection)) {
            displayRestructureViews()
        }
        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }
        binding.menuIcon.setOnClickListener {
            loadFragment(ManageAnxietyFragment())
        }

        var audioFilePath = requireArguments().getString("audioResourceId")

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.makingConnectionRecyclerView)
    }
    private fun displayRestructureViews() {
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_A, 0));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_B, 1));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_C, 2));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_D, 3));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_E, 4));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_F, 5));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_G, 6));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_H, 7));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_I, 8));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_J, 9));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_J_1, 10));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_J_2, 11));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_J_3, 12));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_J_4, 13));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_K, 14));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_K_1, 15));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_K_2, 16));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_K_3, 17));
        restructureText.add( MakingConnectionDataClass(restructureAdapter.VIEW_TYPE_TYPE_K_4, 18));
        restructureAdapter.notifyDataSetChanged()
    }
    private fun initializeAdapter() {
        binding.makingConnectionRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        restructureAdapter = MakingConnectionAdapter(requireContext(), restructureText,this)
        binding.makingConnectionRecyclerView.adapter = restructureAdapter
    }
    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1, true)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1, false)
        }

        binding.makingConnectionRecyclerView.addOnScrollListener(object :
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

                        binding.progressBarRestructure.progress =
                                // currentQuestionIndex * (maxProgress / (restructureText.size - 1))
                            currentQuestionIndex * (maxProgress / (9))

                        updateStepIndicators()
                    }
                }
            }
        })
    }

    fun navigateToQuestion(index: Int, isNext: Boolean) {
        if (index in 0 until restructureText.size) {
            if (isNext) {
                previousQuestionIndex = currentQuestionIndex
            } else {
                // Update the current step indicator to inactive when going to the previous question
                if (currentQuestionIndex >= 0 && currentQuestionIndex < stepIndicators.size) {
                    stepIndicators[currentQuestionIndex].setImageResource(R.drawable.ic_inactivetickmark)
                }
            }
            currentQuestionIndex = index
            binding.makingConnectionRecyclerView.smoothScrollToPosition(currentQuestionIndex)

            // Calculate and set the progress based on the current question index
            binding.progressBarRestructure.progress =
                currentQuestionIndex * (maxProgress / ( stepIndicators.size-1))

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
        if (currentQuestionIndex == restructureText.size - 1) {
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