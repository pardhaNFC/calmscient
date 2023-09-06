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

import com.calmscient.databinding.ActivityRecognizeBinding
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.calmscient.R
import com.calmscient.fragments.DiscoveryFragment

class RecognizeActivity : AppCompat() {
    private lateinit var binding: ActivityRecognizeBinding
    private lateinit var alertDialog: AlertDialog
    private var currentIndex = 0

    // Define an array of screen-specific data
    data class ScreenData(
        val bulbText: List<String>
    )

    /* private val screenData = arrayOf(
         ScreenData(
             listOf("Bulb 1 Text for Screen 1", "Bulb 2 Text for Screen 1", "Bulb 3 Text for Screen 1")
         ),
         ScreenData(
             listOf("Bulb 1 Text for Screen 2", "Bulb 2 Text for Screen 2", "Bulb 3 Text for Screen 2")
         ),
         ScreenData(
             listOf("Bulb 1 Text for Screen 3", "Bulb 2 Text for Screen 3", "Bulb 3 Text for Screen 3")
         )
     )*/

    // Define an array of data arrays for screens 4 to 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecognizeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val images = arrayOf(
            R.drawable.ic_recognize_1,
            R.drawable.ic_recognize_2,
            R.drawable.ic_recognize_3,
        )

        val images2 = arrayOf(
            R.drawable.ic_nora,
            R.drawable.ic_austin,
            R.drawable.ic_melanie,
        )
        binding.icGlossary.setOnClickListener {
            startActivity(Intent(this,GlossaryActivity::class.java))
        }
        binding.menuIcon.setOnClickListener {
            onBackPressed()
        }
        val textArrays = arrayOf(
            arrayOf(
                null,
                getString(R.string.recognize_card1_text1),
                getString(R.string.recognize_card1_text2),
                getString(R.string.recognize_card1_text3)
            ),
            arrayOf(
                null,
                getString(R.string.recognize_card2_text1),
                null,
                getString(R.string.recognize_card2_text2)
            ),
            arrayOf(
                getString(R.string.recognize_card3_text1),
                getString(R.string.recognize_card3_text2),
                null,
                getString(R.string.recognize_card3_text3)
            )
        )

        val dataForScreens4to6 = arrayOf(
            arrayOf(getString(R.string.nora), null, null, null),
            //arrayOf(getString(R.string.austin), getString(R.string.fear), null, null),
            arrayOf(getString(R.string.melanie), null, getString(R.string.how_about_you), getString(R.string.melanie_desc))
        )

        // Initialize the dialog view
        val dialogView = LayoutInflater.from(this).inflate(R.layout.anixity_dialog, null)
        alertDialog = AlertDialog.Builder(this).setView(dialogView).create()

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
            updateUI(textArrays, images, images2, dataForScreens4to6)
        }

        binding.previousQuestion.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI(textArrays, images, images2, dataForScreens4to6)
            }
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



        // Initialize UI with the first set of text and image
        updateUI(textArrays, images, images2,dataForScreens4to6)
    }
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(DiscoveryFragment::class.java.simpleName)
        if (fragment != null && fragment.isVisible) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
    /*private fun updateUI(textArrays: Array<Array<String?>>, images: Array<Int>, image2: Array<Int>,dataForScreens4to6: Array<Array<String?>>) {
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
                text5View.text = newDataArray[0]
                text7View.text = newDataArray[1]
                text8View.text = newDataArray[2]
                text9View.text = newDataArray[3]
                imageView2.setImageResource(image2[dataIndex])
            }
        } else {
            binding.firstThreeScreensUi.visibility = View.VISIBLE
            binding.lastThreeScreensUi.visibility = View.GONE

            if (currentIndex >= 0 && currentIndex < textArrays.size) {
                val currentTextArray = textArrays[currentIndex]
                text1View.text = currentTextArray[0]
                text2View.text = currentTextArray[1]
                text3View.text = currentTextArray[2]
                text4View.text = currentTextArray[3]
                imageView.setImageResource(images[currentIndex])
            }
        }


        val progressBar = binding.recognizeProgressBar
        progressBar.progress = (currentIndex + 1) * 20
        updateTickIcons(currentIndex)
    }
*/

    private fun updateUI(textArrays: Array<Array<String?>>, images: Array<Int>, image2: Array<Int>, dataForScreens4to6: Array<Array<String?>>) {
        val text1View = binding.tvText1
        val text2View = binding.tvText2
        val text3View = binding.tvText3
        val text4View = binding.tvText4
        val imageView = binding.imageView
        val text5View = binding.screen2TvText1
        //val text7View = binding.fearText
        val imageView2 = binding.screen2Image1

        val text8View = binding.howText
        val text9View = binding.howTextDescription

        if (currentIndex == 3 || currentIndex == 4 || currentIndex == 5) {
            binding.firstThreeScreensUi.visibility = View.GONE
            binding.lastThreeScreensUi.visibility = View.VISIBLE

            val dataIndex = currentIndex - 3
            if (dataIndex >= 0 && dataIndex < dataForScreens4to6.size) {
                val newDataArray = dataForScreens4to6[dataIndex]
                text5View.text = newDataArray[0]
                //text7View.text = newDataArray[1]
                text8View.text = newDataArray[2]
                text9View.text = newDataArray[3]
                imageView2.setImageResource(image2[dataIndex])
            }
        } else {
            binding.firstThreeScreensUi.visibility = View.VISIBLE
            binding.lastThreeScreensUi.visibility = View.GONE

            if (currentIndex >= 0 && currentIndex < textArrays.size) {
                val currentTextArray = textArrays[currentIndex]
                text1View.text = currentTextArray[0]
                text2View.text = currentTextArray[1]
                text3View.text = currentTextArray[2]
                text4View.text = currentTextArray[3]
                imageView.setImageResource(images[currentIndex])
            }

            // Check and set visibility for each TextView
            setVisibilityIfBlank(text1View)
            setVisibilityIfBlank(text2View)
            setVisibilityIfBlank(text3View)
            setVisibilityIfBlank(text4View)
            /*setVisibilityIfBlank(text5View)
            setVisibilityIfBlank(text7View)
            setVisibilityIfBlank(text8View)
            setVisibilityIfBlank(text9View)*/
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
    }

    /*private fun showInformationDialog(bulbIndex: Int) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.anixity_dialog, null)
        val infoTextView = dialogView.findViewById<TextView>(R.id.dialogTextView)
        val closeButton = dialogView.findViewById<ImageButton>(R.id.closeButton)

        val dataIndex = currentIndex - 3
        if (dataIndex >= 0 && dataIndex < screenData.size) {
            val dialogData = screenData[dataIndex].bulbText[bulbIndex]
            infoTextView.text = dialogData
            val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
                .setView(dialogView)

            val dialog = dialogBuilder.create()
            dialog.show()

            closeButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }*/

    private fun showInformationDialog(stringResourceIds: List<Int>, title: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.recognize_dialog, null)
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

            val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
                .setView(dialogView)

            val dialog = dialogBuilder.create()
            dialog.show()

            closeButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }


}