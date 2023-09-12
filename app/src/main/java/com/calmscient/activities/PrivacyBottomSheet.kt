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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calmscient.R
import com.calmscient.adapters.PrivacyAdapter
import com.calmscient.databinding.ActivityPrivacyBinding
import com.calmscient.di.remote.PrivacyItemDataClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
class PrivacyBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: ActivityPrivacyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityPrivacyBinding.inflate(layoutInflater)

        binding.closePrivacy.setOnClickListener{
            dismiss()
        }

        binding.titlePrivacy.setText(R.string.privacy)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create a list of items to display in the RecyclerView
        val privacyItemList = listOf(
            PrivacyItemDataClass(getString(R.string.journaling), false),
            PrivacyItemDataClass(getString(R.string.course_work), true),
            PrivacyItemDataClass(getString(R.string.mood), true),
            PrivacyItemDataClass(getString(R.string.sleep), true),
            PrivacyItemDataClass(getString(R.string.medication), false),
            PrivacyItemDataClass(getString(R.string.phq_9_screening), true),
            PrivacyItemDataClass(getString(R.string.gad_7_screening), true),
            PrivacyItemDataClass(getString(R.string.audit_screening), true),
            PrivacyItemDataClass(getString(R.string.dast_screening), true),
            )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPrivacy)

        val adapter = context?.let { PrivacyAdapter(privacyItemList, it) }
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())


    }
}
