package com.calmscient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.R
import com.calmscient.adapters.ProgressWorksAdapter
import com.calmscient.adapters.TakingControlSummaryCardAdapter
import com.calmscient.data.remote.ProgressWorksTask
import com.calmscient.databinding.ProgressoncourseworkFragmentBinding
import com.calmscient.utils.AnimationUtils

class ProgressOnCourseWorkFragment : Fragment() {
    private lateinit var binding: ProgressoncourseworkFragmentBinding
    private val progressWorksTask = mutableListOf<ProgressWorksTask>()
    private lateinit var progressworksadapter: ProgressWorksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            loadFragment(WeeklySummaryFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProgressoncourseworkFragmentBinding.inflate(inflater, container, false)
        binding.backIcon1.setOnClickListener {
            loadFragment(WeeklySummaryFragment())
        }
        binding.needToTalkWithSomeOne.setOnClickListener {
            loadFragment(EmergencyResourceFragment())
        }

        binding.bravingAnxietyImage.setOnClickListener {
            binding.screen1.visibility = View.GONE
            binding.screen2.visibility = View.VISIBLE
            binding.backIcon1.visibility = View.GONE
            binding.backIcon2.visibility = View.VISIBLE
        }

        binding.backIcon2.setOnClickListener {
            binding.screen1.visibility = View.VISIBLE
            binding.screen2.visibility = View.GONE
            binding.backIcon1.visibility = View.VISIBLE
            binding.backIcon2.visibility = View.GONE
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Initialize the RecyclerView and Adapter here
        binding.recyclerViewProgressWorks.layoutManager = LinearLayoutManager(requireContext())
        progressworksadapter = ProgressWorksAdapter(progressWorksTask)
        binding.recyclerViewProgressWorks.adapter = progressworksadapter
        progressworksadapter.updateTasks(progressWorksTask)
        displayCardViews()
    }

    private fun displayCardViews() {
        progressWorksTask.clear()
        val tasks = listOf(
            ProgressWorksTask(
                getString(R.string.bravinganxiety_anxietyworks),
                "100%",
                getString(R.string.progresswork_expand1_text1),
                "100%",
                getString(R.string.progresswork_expand1_text2),
                "100%",
                getString(R.string.progresswork_expand1_text3),
                "100%",
                getString(R.string.progresswork_expand1_text4),
                "100%"
            ),
            ProgressWorksTask(
                getString(R.string.braving_sleep),
                "100%",
                getString(R.string.progresswork_expand2_text1),
                "100%",
                getString(R.string.progresswork_expand2_text2),
                "100%",
                getString(R.string.progresswork_expand2_text3),
                "100%",
                getString(R.string.progresswork_expand2_text4),
                "100%"
            ),
            ProgressWorksTask(
                getString(R.string.braving_building),
                "100%",
                getString(R.string.progresswork_expand3_text1),
                "100%",
                getString(R.string.progresswork_expand3_text2),
                "100%",
                getString(R.string.progresswork_expand3_text3),
                "100%",
                getString(R.string.progresswork_expand3_text4),
                "100%"
            )
        )
        progressWorksTask.addAll(tasks)
        progressworksadapter.notifyDataSetChanged()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
