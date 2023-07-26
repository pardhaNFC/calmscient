package com.calmscient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.databinding.ActivityMyMedicalRecordsBinding

class MedicationsFragment : Fragment() {
    private lateinit var binding: ActivityMyMedicalRecordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMyMedicalRecordsBinding.inflate(inflater, container, false)
        binding.medicationsLayout.setOnClickListener {
            Toast.makeText(requireActivity(), "message", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }
}