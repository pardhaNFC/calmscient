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
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.FragmentMedicationDetailBinding

class MedicationDetail: Fragment() {
    private lateinit var binding: FragmentMedicationDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicationDetailBinding.inflate(inflater, container, false)

        binding.backIcon.setOnClickListener{
            loadFragment(CalendarFragment())
        }
        return binding.root;
        // return inflater.inflate(R.layout.fragment_medication_detail, container, false)
    }

    private fun loadFragment(fragment: Fragment)
    {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}