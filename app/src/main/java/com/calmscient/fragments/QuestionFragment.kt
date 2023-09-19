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
import com.calmscient.activities.CommonDialog
import com.calmscient.adapters.QuestionAdapter
import com.calmscient.databinding.FragmentQuestionBinding
import com.calmscient.utils.common.SavePreferences

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
    lateinit var savePrefData: SavePreferences
    private var currentQuestionIndex = 0
    private var isPreviousButtonVisible = false
    private var isNextButtonVisible = true // Initially, show the next button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(ScreeningsFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleP = "PHQ-9"
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
        questionAdapter = QuestionAdapter(requireContext(), questions, titleP)
        binding.questionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = questionAdapter
        }

        // Create an instance of the CommonDialog class
        val commonDialog = CommonDialog(requireContext())

        // Show a dialog when the fragment is loaded
        commonDialog.showDialog(getString(R.string.phq))
        // Use a PagerSnapHelper for snapping to a question's position
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.questionsRecyclerView)


        // Call toggleButtonVisibility to set the initial button visibility
        toggleButtonVisibility()

        setupNavigation()

        binding.backIcon.setOnClickListener {
            loadFragment(ScreeningsFragment())
        }
    }

    private fun generateDummyQuestions(): List<Question> {
        val questionsList = mutableListOf<Question>()
        if (savePrefData.getSpanLanguageState() == true) {
            val questionTexts = listOf(
                "1. Poco interés o placer en hacer cosas",
                "2. Se ha sentido decaído(a), deprimido(a) o sin esperanzas",
                "3. Ha tenido dificultad para quedarse o permanecer dormido(a), o ha dormido demasiado ",
                "4. Se ha sentido cansado(a) o con poca energía",
                "5. Sin apetito o ha comido en exceso",
                "6. Se ha sentido mal con usted mismo(a) – o que es un fracaso o que ha quedado mal con usted mismo(a) o con su familia",
                "7. Ha tenido dificultad para concentrarse en ciertas actividades, tales como leer el periódico o ver la televisión",
                "8. ¿Se ha movido o hablado tan lento que otras personaspodrían haberlo notado? o lo contrario – muy inquieto(a) o agitado(a) que ha estado moviéndose mucho más delo normal",
                "9. Pensamientos de que estaría mejor muerto(a) o de lastimarse de alguna manera ",
                "10. Si marcó algún problema, ¿qué tan difícil le resulta hacer su trabajo, ocuparse de las cosas en casa o llevarse bien con otras personas?"
            )

            for (questionText in questionTexts) {
                val options =
                    listOf(
                        "Ningún día",
                        "Varios días",
                        "Más de la mitad de los días",
                        "Casi todos los días"
                    )
                questionsList.add(Question(questionText, options))
            }
        } else {
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
                    listOf(
                        "Not at all",
                        "Several days",
                        "More than half the days",
                        "Nearly every day"
                    )
                questionsList.add(Question(questionText, options))
            }
        }

        return questionsList
    }

    /* private fun setupNavigation() {
         binding.nextQuestion.setOnClickListener {
             binding.previousQuestion.visibility = View.VISIBLE
             navigateToQuestion(currentQuestionIndex + 1)
         }

         binding.previousQuestion.setOnClickListener {
             if(currentQuestionIndex == 1)
             {
                 binding.previousQuestion.visibility = View.GONE
             }
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
     }*/

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


    private fun navigateToQuestion(index: Int) {
        val questions: List<Question> = generateDummyQuestions()
        val totalQuestions = questions.size
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