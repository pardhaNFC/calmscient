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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.activities.CommonDialog
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.databinding.FragmentADUITQuestionBinding
import com.calmscient.databinding.FragmentDASTQuestionBinding
import com.calmscient.databinding.FragmentGadQuestionsBinding
import com.calmscient.utils.common.SavePreferences

class DASTQuestionFragment : Fragment() {
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var binding: FragmentDASTQuestionBinding
    lateinit var savePrefData: SavePreferences

    private var currentQuestionIndex = 0
    private var isPreviousButtonVisible = false
    private var isNextButtonVisible = true // Initially, show the next button


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
        binding = FragmentDASTQuestionBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        binding.previousQuestion.visibility = View.GONE
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
        val titleD = "DAST-10"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = QuestionAdapter(requireContext(),questions,titleD)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }
        // Create an instance of the CommonDialog class
        val commonDialog = CommonDialog(requireContext())

        // Show a dialog when the fragment is loaded
        commonDialog.showDialog(getString(R.string.dast))
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
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. ¿Ha utilizado medicamentos distintos de los necesarios por motivos médicos?",
                "2. ¿Abusa de más de una droga a la vez?",
                "3. ¿Puede dejar de consumir drogas cuando quiera?",
                "4. ¿Ha experimentado pérdida temporal de la memoria o escenas retrospectivas como resultado del uso de drogas?",
                "5. ¿En ocasiones se siente mal o culpable por su uso de drogas?",
                "6. ¿Se quejan alguna vez su cónyuge (o padres) por su implicación con las drogas?",
                "7. ¿Ha descuidado a su familia debido a su uso de drogas?",
                "8. ¿Ha participado en actividades ilegales a fin de obtener drogas?",
                "9. ¿Ha experimentado alguna vez síntomas de abstinencia (sentirse enfermo) cuando ha dejado de consumir drogas? ",
                "10. ¿Ha tenido problemas médicos como resultado de su uso de drogas (por ejemplo, pérdida de memoria, hepatitis, convulsiones, hemorragias)?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = listOf("No","Sí")
                questionsList.add(Question(questionText, options))
            }
        }else{
            val questionTexts = listOf(
                "1. Have you used drugs other than those required for medical reasons? ",
                "2. Do you use more than one drug at a time?",
                "3. Are you always able to stop using drugs when you want to?",
                "4. Have you had \"blackouts\" or \"flashbacks\" as a result of drug use?",
                "5. Do you ever feel bad or guilty about your drug use?",
                "6. Does your spouse (or parents) ever complain about your involvement with drugs?",
                "7. Have you neglected your family because of your use of drugs?",
                "8. Have you engaged in illegal activities in order to obtain drugs?",
                "9. Have you ever experienced withdrawal symptoms (felt sick) when you stopped taking drugs?",
                "10.  Have you had medical problems as a result of your drug use (e.g., memory loss, hepatitis, convulsions, bleeding, etc.)?"
            )

            for (index in questionTexts.indices) {
                val questionText = questionTexts[index]
                val options = listOf("No","Yes")
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
        binding.questionsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    private fun loadFragment(fragment: Fragment) {
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
        binding.previousQuestion.visibility = if (isPreviousButtonVisible) View.VISIBLE else View.GONE
        binding.nextQuestion.visibility = if (isNextButtonVisible) View.VISIBLE else View.GONE
    }
}