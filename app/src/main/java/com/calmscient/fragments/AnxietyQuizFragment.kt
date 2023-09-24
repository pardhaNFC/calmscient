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

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.LayoutAnxietyQuizBinding
import com.calmscient.utils.common.SavePreferences

class AnxietyQuizFragment : Fragment() {
    private lateinit var binding: LayoutAnxietyQuizBinding
    private var selectedOptionSection1: TextView? = null
    private var selectedOptionSection2: TextView? = null
    private var selectedOptionSection3: TextView? = null
    lateinit var savePrefData: SavePreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutAnxietyQuizBinding.inflate(inflater, container, false)
        savePrefData = SavePreferences(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/

        val tvWork = binding.tvWork
        val tvPersonal = binding.tvPersonal
        val tvFamily = binding.tvFamily
        val tvSocial = binding.tvSocial
        val tvAcute = binding.tvAcute

        val tvPushAway = binding.tvPushAway
        val tvPushThrough = binding.tvPushThrough
        val tvDenial = binding.tvDenial

        val tv3_1 = binding.tv31
        val tv3_2 = binding.tv32
        val tv3_3 = binding.tv33

        if(savePrefData.getSpanLanguageState() == true){
            binding.quizImage.setImageResource(R.drawable.spanish_quiz)
        }else{
            binding.quizImage.setImageResource(R.drawable.ic_an_quiz)
        }


        // Section 1
        tvWork.setOnClickListener { selectOptionSection1(tvWork) }
        tvPersonal.setOnClickListener { selectOptionSection1(tvPersonal) }
        tvFamily.setOnClickListener { selectOptionSection1(tvFamily) }
        tvSocial.setOnClickListener { selectOptionSection1(tvSocial) }
        tvAcute.setOnClickListener { selectOptionSection1(tvAcute) }

        // Section 2
        tvPushAway.setOnClickListener { selectOptionSection2(tvPushAway) }
        tvPushThrough.setOnClickListener { selectOptionSection2(tvPushThrough) }
        tvDenial.setOnClickListener { selectOptionSection2(tvDenial) }

        // Section 3
        tv3_1.setOnClickListener { selectOptionSection3(tv3_1) }
        tv3_2.setOnClickListener { selectOptionSection3(tv3_2) }
        tv3_3.setOnClickListener { selectOptionSection3(tv3_3) }

        binding.menuIcon.setOnClickListener {
           // requireActivity().onBackPressed()
            //loadFragment(ManageAnxietyFragment())
            requireActivity().onBackPressed()
        }
    }

    private fun selectOptionSection1(selectedTextView: TextView) {
        selectedOptionSection1?.apply {
            setBackgroundResource(R.drawable.anxiety_border)
            setTextColor(Color.parseColor("#424242"))
        }

        selectedTextView.setBackgroundResource(R.drawable.anxiety_selected)
        selectedTextView.setTextColor(Color.parseColor("#FFFFFF"))

        selectedOptionSection1 = selectedTextView
    }

    private fun selectOptionSection2(selectedTextView: TextView) {
        selectedOptionSection2?.apply {
            setBackgroundResource(R.drawable.anxiety_border)
            setTextColor(Color.parseColor("#424242"))
        }

        selectedTextView.setBackgroundResource(R.drawable.anxiety_selected)
        selectedTextView.setTextColor(Color.parseColor("#FFFFFF"))

        selectedOptionSection2 = selectedTextView
    }

    private fun selectOptionSection3(selectedTextView: TextView) {
        selectedOptionSection3?.apply {
            setBackgroundResource(R.drawable.anxiety_border)
            setTextColor(Color.parseColor("#424242"))
        }

        selectedTextView.setBackgroundResource(R.drawable.anxiety_selected)
        selectedTextView.setTextColor(Color.parseColor("#FFFFFF"))

        selectedOptionSection3 = selectedTextView
    }

   /* override fun onBackPressed() {
        val fragment =
            requireActivity().supportFragmentManager.findFragmentByTag(DiscoveryFragment::class.java.simpleName)
        if (fragment != null && fragment.isVisible) {
            requireActivity().supportFragmentManager.popBackStack()
        } else {
            requireActivity().onBackPressed()
        }
    }*/

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
