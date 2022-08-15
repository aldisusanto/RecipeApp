package com.android.recipeapp.ui.instruction


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.recipeapp.R
import com.android.recipeapp.databinding.InstructionsFragmentBinding
import com.android.recipeapp.ui.Detail.DetailFragment

class InstructionsFragment : Fragment() {

    private var _binding: InstructionsFragmentBinding? = null
    private val binding get() = _binding
    private lateinit var instructionView: TextView
    private lateinit var instruction: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        instruction = args?.getString(DetailFragment.EXTRA_INSTRUCTION).toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = InstructionsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {
        instructionView = view.findViewById(R.id.instruction)
        instructionView.text = instruction

    }


}