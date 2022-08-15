package com.android.recipeapp.ui.Search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.recipeapp.adapter.RecipesAdapter
import com.android.recipeapp.databinding.FragmentSearchBinding
import com.android.recipeapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var mealAdapter: RecipesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

    }

    private fun setupUI() {
        mealAdapter = RecipesAdapter()
        binding.apply {
            setupInitialUI()
            viewModel.searchResult.observe(viewLifecycleOwner, { response ->

                when (response) {
                    is Resource.Success -> {
                        val dataArray = response.data!!.meals
                        if (dataArray == null || dataArray.isEmpty()) {
                            textView2.visibility = View.VISIBLE
                            listRecipe.visibility = View.GONE
                            setSearchable(true)
                            progressBar(false)
                        } else {
                            mealAdapter.differ.submitList(response.data.meals.toList())
                            listRecipe.visibility = View.VISIBLE
                            progressBar(false)
                            setSearchable(true)
                            textView2.visibility = View.GONE
                        }


                    }
                    is Resource.Error -> {
                        response.message?.let { message ->

                            Toast.makeText(
                                activity,
                                "an error occured $message",
                                Toast.LENGTH_SHORT
                            ).show()
                            setSearchable(true)
                        }
                    }
                    is Resource.Loading -> {
                        setSearchable(false)
                        progressBar(true)
                    }

                    else -> {
                        textView2.visibility = View.VISIBLE
                    }


                }


            })
            with(binding.listRecipe) {
                this.setHasFixedSize(true)
                this.layoutManager = GridLayoutManager(requireContext(), 2)
                this.adapter = mealAdapter


            }
        }
    }

    private fun setupInitialUI() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        searchMeal(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })

        }
    }

    private fun searchMeal(query: String) {
        setSearchable(true)
        if (query.isEmpty()) {
            return
        }

        viewModel.getSearchMeal(query)
    }

    private fun setSearchable(state: Boolean) {
        binding.apply {
            searchView.isEnabled = state
            searchView.isSubmitButtonEnabled = state
            searchView.isFocusableInTouchMode = state

            if (state) {
                searchView.requestFocus()
            } else {
                searchView.clearFocus()
            }
        }

    }


    private fun progressBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null // avoiding memory leaks
    }

}