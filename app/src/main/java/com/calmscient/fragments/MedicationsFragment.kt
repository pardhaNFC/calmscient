package com.calmscient.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.activities.SettingsActivity
import com.calmscient.databinding.FragmentMyMedicalRecordsBinding

class MedicationsFragment : Fragment() {
    private lateinit var binding: FragmentMyMedicalRecordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyMedicalRecordsBinding.inflate(inflater, container, false)
        binding.medicationsLayout.setOnClickListener {
            //Toast.makeText(requireActivity(), "message", Toast.LENGTH_LONG).show()
            loadFragment(CalendarFragment())
        }
        binding.upcomingAppointmentsLayout.setOnClickListener {
            Toast.makeText(requireActivity(), "Coming Soon", Toast.LENGTH_LONG).show()
        }
        binding.screeningsLayout.setOnClickListener {
            Toast.makeText(requireActivity(), "Coming Soon", Toast.LENGTH_LONG).show()
        }
        binding.backIcon.setOnClickListener {
            loadFragment(HomeFragment())
        }
        binding.icProfile.setOnClickListener {
            openSettingsActivity()
        }
        return binding.root
    }

    private fun openSettingsActivity() {
        val intent = Intent(activity, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.commit()
    }
}