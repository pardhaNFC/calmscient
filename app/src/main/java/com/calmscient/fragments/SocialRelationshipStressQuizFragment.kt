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
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.CommonDialog
import com.calmscient.adapters.OnOptionSelectedListener
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.adapters.YourStressTriggerQuizAdapter
import com.calmscient.databinding.FragmentFamilyRelatedStressQuizBinding
import com.calmscient.databinding.FragmentGadQuestionsBinding
import com.calmscient.databinding.FragmentPersonalHealthStressQuizBinding
import com.calmscient.databinding.FragmentSocialRelationshipStressQuizBinding
import com.calmscient.databinding.FragmentWorkRelatedStressQuizBinding
import com.calmscient.utils.common.SavePreferences

class SocialRelationshipStressQuizFragment : Fragment(), OnOptionSelectedListener {

    private lateinit var questionAdapter: YourStressTriggerQuizAdapter
    private lateinit var binding: FragmentSocialRelationshipStressQuizBinding
    lateinit var savePrefData: SavePreferences
    private var currentQuestionIndex = 0
    private var isPreviousButtonVisible = false
    private var isNextButtonVisible = true // Initially, show the next button

    override fun onOptionSelected(isYesSelected: Boolean) {
        if (isYesSelected) {
            val fragmentTag = this.javaClass.simpleName // Get the fragment tag
            val fragment = YesOptionFragment.newInstance(fragmentTag,getString(R.string.social_relationship_stress_quiz))
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flFragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(YourStressTriggersQuizFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSocialRelationshipStressQuizBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        binding.previousQuestion.visibility = View.GONE

        binding.backIcon.setOnClickListener{
            loadFragment(YourStressTriggersQuizFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = this
        val titleG = "GAD-7"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = YourStressTriggerQuizAdapter(requireContext(), questions, titleG,fragment)
        binding.socialRelationshipStressQuizRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.socialRelationshipStressQuizRecyclerView)
        setupNavigation()
        binding.backIcon.setOnClickListener {
            loadFragment(YourStressTriggersQuizFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. Are you experiencing communication difficulties? (Lack of assertiveness, people pleasing, can’t get along with others)",
                "2. Are you experiencing anxiety in social settings? (Social/performance anxiety, sensory processing sensitivity)",
                "3. Are you experiencing a lack of friendship or are you failing in maintaining your friendships?",
                "4. Are you experiencing harassment or bullying at school, work, or other social circles?",
                "5. Are you lacking a support system or the resources to manage your stress?",
                "6. Are you experiencing loneliness?"

            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options =
                    // Default options for other questions
                    listOf(
                        "No",
                        "Sometimes",
                        "Often",
                        "Constantly"
                    )

                questionsList.add(Question(questionText, options))
            }
        } else{
            val questionTexts = listOf(
                "1. Are you experiencing communication difficulties? (Lack of assertiveness, people pleasing, can’t get along with others)",
                "2. Are you experiencing anxiety in social settings? (Social/performance anxiety, sensory processing sensitivity)",
                "3. Are you experiencing a lack of friendship or are you failing in maintaining your friendships?",
                "4. Are you experiencing harassment or bullying at school, work, or other social circles?",
                "5. Are you lacking a support system or the resources to manage your stress?",
                "6. Are you experiencing loneliness?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options =
                    // Default options for other questions
                    listOf(
                        "No",
                        "Sometimes",
                        "Often",
                        "Constantly"
                    )

                questionsList.add(Question(questionText, options))
            }

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

        binding.socialRelationshipStressQuizRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if the user is scrolling horizontally
                if (Math.abs(dx) > Math.abs(dy)) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    // Update the current question index
                    currentQuestionIndex = firstVisibleItemPosition
                    // Toggle the visibility of the buttons based on the current index
                    toggleButtonVisibility()
                }
            }
        })
    }


    private fun navigateToQuestion(index: Int) {
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        if (index in 0 until questions.size) {
            currentQuestionIndex = index
            binding.socialRelationshipStressQuizRecyclerView.smoothScrollToPosition(currentQuestionIndex)
        } else {
            if (currentQuestionIndex == questions.size - 1) {
                loadFragment(ResultsFragment())
                //showResult()
            }
        }
    }

    private fun showResult() {
        val toastMessage = "You've reached the end of the questions!"
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun loadFragment(fragment: Fragment) {

        val bundle = Bundle()
        bundle.putString("description", getString(R.string.your_stress_triggers_quiz))
        bundle.putString("title", "Your social/relationship stress report")
        bundle.putInt(ResultsFragment.SOURCE_SCREEN_KEY, ResultsFragment.YOUR_STRESS_FRAGMENT)
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun toggleButtonVisibility() {
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        isPreviousButtonVisible = currentQuestionIndex > 0
        isNextButtonVisible = currentQuestionIndex < totalQuestions - 1


        // Always show both "Previous" and "Next" buttons/icons for the last question
        if (currentQuestionIndex >= questions.size - 1) {
            isPreviousButtonVisible = true
            isNextButtonVisible = true
        }
        binding.previousQuestion.visibility =
            if (isPreviousButtonVisible) View.VISIBLE else View.GONE
        binding.nextQuestion.visibility = if (isNextButtonVisible) View.VISIBLE else View.GONE
    }

}