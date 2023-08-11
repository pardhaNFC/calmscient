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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.databinding.FragmentGadQuestionsBinding
class GADQuestionFragment  : Fragment() {

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var binding: FragmentGadQuestionsBinding
    private val questions: List<Question> = generateDummyQuestions()
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGadQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the selected title from arguments
        val selectedTitle = arguments?.getString("selectedTitle")

        // Update your UI with the selected title
        if (!selectedTitle.isNullOrEmpty()) {
            binding.titleTextView.text = selectedTitle
        }
        questionAdapter = QuestionAdapter(questions)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.questionsRecyclerView)

        setupNavigation()

        binding.backIcon.setOnClickListener{
            loadFragment(ScreeningsFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()

        val questionTexts = listOf(
            "1. Feeling nervous, anxious or on edge",
            "2. Not being able to stop or control worrying",
            "3. Worrying too much about different things",
            "4. Trouble relaxing",
            "5. Being so restless that it is hard to sit still",
            "6. Becoming easily annoyed or irritable",
            "7. Feeling afraid as if something awful might happen",
            "8. If you checked off any problems, how difficult have these problems made it for you to do your work, take care of things at home, or get along with other people?"
        )

        for (index in questionTexts.indices) {
            val questionText = questionTexts[index]
            val options = if (index == 7) {
                // Custom options for the 8th question
                listOf("Not difficult at all", "Somewhat difficult", "Very difficult", "Extremely difficult")
            } else {
                // Default options for other questions
                listOf("Not at all", "Several days", "More than half the days", "Nearly every day")
            }
            questionsList.add(Question(questionText, options))
        }
        return questionsList
    }
    private fun setupNavigation() {
        binding.nextQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex + 1)
        }

        binding.previousQuestion.setOnClickListener {
            navigateToQuestion(currentQuestionIndex - 1)
        }

        binding.questionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition != currentQuestionIndex) {
                        currentQuestionIndex = firstVisibleItemPosition
                    }
                }
            }
        })
    }


    private fun navigateToQuestion(index: Int) {
        if (index in 0 until questions.size) {
            currentQuestionIndex = index
            binding.questionsRecyclerView.smoothScrollToPosition(currentQuestionIndex)
        }else{
            if(currentQuestionIndex == questions.size-1){
                loadFragment(ResultsFragment())
            }
        }
    }
    private fun showResult() {
        val toastMessage = "You've reached the end of the questions!"
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun loadFragment(fragment: Fragment)
    {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }


}