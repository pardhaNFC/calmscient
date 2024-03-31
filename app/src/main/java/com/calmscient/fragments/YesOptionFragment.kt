package com.calmscient.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import com.calmscient.R
import com.calmscient.activities.GlossaryActivity
import com.calmscient.databinding.LayoutYesOptionBinding

class YesOptionFragment : Fragment() {
    private lateinit var binding: LayoutYesOptionBinding
    private lateinit var optionSets: List<List<LinearLayout>>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutYesOptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        title?.let { binding.tvTitle.text = it }

        binding.icGlossary.setOnClickListener {
            startActivity(Intent(requireContext(), GlossaryActivity::class.java))
        }

        binding.menuIcon.setOnClickListener {
            val fragmentTag = arguments?.getString("fragmentTag")
            fragmentTag?.let {
                loadFragmentByTag(it)
            }
        }

         optionSets = listOf(
            listOf(
                binding.optionOneLayout,
                binding.optionTwoLayout,
                binding.optionThreeLayout,

            )
        )

        optionSets.forEachIndexed { setIndex, set ->
            set.forEachIndexed { optionIndex, textView ->
                textView.setOnClickListener {
                    onOptionClicked(setIndex, optionIndex)
                }
            }
        }
    }

    private fun loadFragmentByTag(fragmentTag: String) {
        val fragment = when (fragmentTag) {
            FamilyRelatedStressQuizFragment::class.java.simpleName -> FamilyRelatedStressQuizFragment()
            WorkRelatedStressQuizFragment::class.java.simpleName -> WorkRelatedStressQuizFragment()
            PersonalHealthStressQuizFragment::class.java.simpleName -> PersonalHealthStressQuizFragment()
            else -> null
        }
        fragment?.let {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, it)
                .addToBackStack(null)
                .commit()
        }
    }

    private val selectedOptionIndices: MutableList<Int> = MutableList(1) { -1 }

    private fun onOptionClicked(setIndex: Int, selectedIndex: Int) {
        if (selectedIndex != selectedOptionIndices[setIndex]) {
            selectedOptionIndices[setIndex] = selectedIndex
            clearSelection(setIndex)
            selectOption(setIndex, selectedIndex)
        }
    }

    private fun selectOption(setIndex: Int, selectedIndex: Int) {
        // Reset background color and text color of all options to default
        clearSelection(setIndex)

        // Set background color and text color of selected option
        val selectedBackgroundColor = Color.parseColor("#6E6BB3") // Change to your desired background color
        val selectedTextColor = Color.WHITE // Change to your desired text color
        val selectedOptionLayout = optionSets[setIndex][selectedIndex]
        selectedOptionLayout.setBackgroundColor(selectedBackgroundColor)
        selectedOptionLayout.forEach { view ->
            if (view is TextView) {
                view.setTextColor(selectedTextColor)
            }
        }

//        // Toast message indicating the option selected
//        val selectedOption = (selectedIndex + 1).toString() // Convert index to option number
//        val message = "Option $selectedOption selected"
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun clearSelection(setIndex: Int) {
        val defaultBackgroundColor = Color.WHITE // Change to your desired default background color
        val defaultTextColor = Color.BLACK // Change to your desired default text color

        optionSets[setIndex].forEach { layout ->
            layout.setBackgroundColor(defaultBackgroundColor)
            layout.forEach { view ->
                if (view is TextView) {
                    view.setTextColor(defaultTextColor)
                }
            }
        }
    }



    companion object {
        fun newInstance(fragmentTag: String, title: String): YesOptionFragment {
            val fragment = YesOptionFragment()
            val args = Bundle()
            args.putString("fragmentTag", fragmentTag)
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }
}
