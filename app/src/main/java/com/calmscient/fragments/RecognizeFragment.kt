package com.calmscient.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.ActivityRecognizeBinding

class RecognizeFragment : Fragment(R.layout.activity_recognize) {
    private lateinit var binding: ActivityRecognizeBinding
    private lateinit var alertDialog: AlertDialog
    private var currentIndex = 0

    // Define an array of screen-specific data
    private val textArrays = listOf(
        arrayOf(null, R.string.recognize_card1_text1, R.string.recognize_card1_text2, R.string.recognize_card1_text3),
        arrayOf(null, R.string.recognize_card2_text1, null, R.string.recognize_card2_text2),
        arrayOf(R.string.recognize_card3_text1, R.string.recognize_card3_text2, null, R.string.recognize_card3_text3)
    )

    private val images = listOf(
        R.drawable.ic_recognize,
        R.drawable.ic_recognize_2,
        R.drawable.ic_recognize_3
    )

    private val images2 = listOf(
        R.drawable.ic_nora,
        R.drawable.ic_austin,
        R.drawable.ic_melanie
    )

    private val dataForScreens4to6 = listOf(
        arrayOf(R.string.nora, null, null, null),
        arrayOf(R.string.austin, null, null, null),
        arrayOf(R.string.melanie, null, R.string.how_about_you, R.string.melanie_desc)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ActivityRecognizeBinding.bind(view)
        binding.previousQuestion.visibility = View.GONE

        // Set click listeners for your buttons here
        binding.nextQuestion.setOnClickListener {
            if (currentIndex < textArrays.size - 1) {
                currentIndex++
            } else if (currentIndex == textArrays.size - 1 && currentIndex < textArrays.size + dataForScreens4to6.size - 1) {
                currentIndex = 3
            } else if (currentIndex == 3 && currentIndex + 1 < textArrays.size + dataForScreens4to6.size) {
                currentIndex = 4
            } else if (currentIndex == 4 && currentIndex + 1 < textArrays.size + dataForScreens4to6.size) {
                currentIndex = 5
            }
            updateUI()
        }

        binding.previousQuestion.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }

        binding.icGlossary.setOnClickListener {
            //startActivity(Intent(requireContext(), GlossaryActivity::class.java))
            loadFragment(GlossaryFragment())
        }

        binding.stressfulBulb.setOnClickListener {
            showInformationDialog(
                listOf(R.string.nora_ss, R.string.austin_ss, R.string.melanie_ss),
                getString(R.string.title_stressful_situation)
            )
        }

        binding.consequencesBulb.setOnClickListener {
            showInformationDialog(
                listOf(R.string.nora_c, R.string.austin_c, R.string.melanie_c),
                getString(R.string.title_consequences)

            )
        }

        binding.responseBulb.setOnClickListener {
            showInformationDialog(

                listOf(R.string.nora_rs, R.string.austin_rs, R.string.melanie_rs),
                getString(R.string.title_response_to_stress)


            )
        }

        binding.menuIcon.setOnClickListener{
            //loadFragment(ManageAnxietyFragment())
            requireActivity().onBackPressed()
        }

        // Initialize UI with the first set of text and image
        updateUI()
    }

    private fun updateUI() {
        val text1View = binding.tvText1
        val text2View = binding.tvText2
        val text3View = binding.tvText3
        val text4View = binding.tvText4
        val imageView = binding.imageView
        val text5View = binding.screen2TvText1
        val text7View = binding.fearText
        val imageView2 = binding.screen2Image1
        val text8View = binding.howText
        val text9View = binding.howTextDescription

        if (currentIndex == 3 || currentIndex == 4 || currentIndex == 5) {
            binding.firstThreeScreensUi.visibility = View.GONE
            binding.lastThreeScreensUi.visibility = View.VISIBLE

            val dataIndex = currentIndex - 3
            if (dataIndex >= 0 && dataIndex < dataForScreens4to6.size) {
                val newDataArray = dataForScreens4to6[dataIndex]
                text5View.text = newDataArray[0]?.let { getString(it) }
                text7View.text = newDataArray[1]?.let { getString(it) }
                text8View.text = newDataArray[2]?.let { getString(it) }
                text9View.text = newDataArray[3]?.let { getString(it) }
                imageView2.setImageResource(images2[dataIndex])
            }
        } else {
            binding.firstThreeScreensUi.visibility = View.VISIBLE
            binding.lastThreeScreensUi.visibility = View.GONE

            if (currentIndex >= 0 && currentIndex < textArrays.size) {
                val currentTextArray = textArrays[currentIndex]
                text1View.text = currentTextArray[0]?.let { getString(it) }
                text2View.text = currentTextArray[1]?.let { getString(it) }
                text3View.text = currentTextArray[2]?.let { getString(it) }
                text4View.text = currentTextArray[3]?.let { getString(it) }
                imageView.setImageResource(images[currentIndex])
            }

            // Check and set visibility for each TextView
            setVisibilityIfBlank(text1View)
            setVisibilityIfBlank(text2View)
            setVisibilityIfBlank(text3View)
            setVisibilityIfBlank(text4View)
        }

        val progressBar = binding.recognizeProgressBar
        progressBar.progress = (currentIndex + 1) * 20
        updateTickIcons(currentIndex)
    }

    private fun setVisibilityIfBlank(textView: TextView) {
        if (textView.text.isNullOrBlank()) {
            textView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
        }
    }

    private fun updateTickIcons(currentIndex: Int) {
        val activeTickDrawable = R.drawable.ic_activetickmark
        val inactiveTickDrawable = R.drawable.ic_inactivetickmark

        val indicatorViews = listOf(
            binding.step1Indicator,
            binding.step2Indicator,
            binding.step3Indicator,
            binding.step4Indicator,
            binding.step5Indicator,
            binding.step6Indicator
        )

        for (i in 0 until indicatorViews.size) {
            val imageView = indicatorViews[i]

            if (i <= currentIndex) {
                imageView.setImageResource(activeTickDrawable)
            } else {
                imageView.setImageResource(inactiveTickDrawable)
            }
        }

        binding.previousQuestion.visibility = if (currentIndex == 0) View.GONE else View.VISIBLE
        binding.nextQuestion.visibility = if (currentIndex == indicatorViews.size - 1) View.GONE else View.VISIBLE
    }

    private fun showInformationDialog(stringResourceIds: List<Int>, title: String) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.recognize_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageView>(R.id.closeButton)
        val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)

        val dataIndex = currentIndex - 3
        if (dataIndex >= 0 && dataIndex < stringResourceIds.size) {
            val stringResourceId = stringResourceIds[dataIndex]
            val dialogText = getString(stringResourceId)
            infoTextView.text = dialogText

            // Set the title based on the provided parameter
            titleTextView.text = title

            val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
                .setView(dialogView)

            val dialog = dialogBuilder.create()
            dialog.show()

            closeButton.setOnClickListener {
                dialog.dismiss()
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
