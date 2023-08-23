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
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.databinding.FragmentQuestionBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

data class Question(
    val questionText: String,
    val options: List<String>,
    var selectedOption: Int = -1
)

class QuestionFragment : Fragment() {
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var binding: FragmentQuestionBinding
    private val questions: List<Question> = generateDummyQuestions()
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            loadFragment(ScreeningsFragment())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleP = "PHQ-9"
        questionAdapter = QuestionAdapter(requireContext(),questions,titleP)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.questionsRecyclerView)

        setupNavigation()

        binding.backIcon.setOnClickListener {
            loadFragment(ScreeningsFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()

        val questionTexts = listOf(
            "1. Little interest or pleasure in doing things",
            "2. Feeling down, depressed, or hopeless",
            "3. Trouble falling or staying asleep, or sleeping too much",
            "4. Feeling tired or having little energy",
            "5. Poor appetite or overeating",
            "6. Feeling bad about yourself-or that you are a failure or have let yourself or your family down",
            "7. Trouble concentrating on things, such as reading the newspaper or watching television",
            "8. Moving or speaking so slowly that other people could have noticed? Or the opposite — being so fidgety or restless that you have been moving around a lot more than usual",
            "9. Thoughts that you would be better off dead or of hurting yourself in some way",
            "10. If you checked off any problems, how difficult have these problems made it for you to do your work, take care of things at home, or get along with other people?"


        )

        for (questionText in questionTexts) {
            val options =
                listOf("Not at all", "Several days", "More than half the days", "Nearly every day")
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
        } else {
            if (currentQuestionIndex == questions.size - 1) {
                loadFragment(ResultsFragment())
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}