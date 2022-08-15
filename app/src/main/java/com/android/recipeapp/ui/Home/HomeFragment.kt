package com.android.recipeapp.ui.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.recipeapp.R
import com.android.recipeapp.adapter.CategoryAdapter
import com.android.recipeapp.adapter.RecipesAdapter
import com.android.recipeapp.databinding.FragmentHomeBinding
import com.android.recipeapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var adapterCategories: CategoryAdapter? = null
    private var mealsAdapter: RecipesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterCategories = CategoryAdapter()
        mealsAdapter = RecipesAdapter()

        adapterCategories?.setOnItemClickCallback(onItemClickedCategory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar(true)
        setUI()

    }

    private fun setUI() {

        viewModel.categories.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let { categoriesResponse ->
                        adapterCategories!!.differ.submitList(categoriesResponse.category.toList())
                        progressBar(false)
                        setupListCategoryUI()
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    progressBar(true)
                }

            }
        })

        binding.refreshHome.apply {
            setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.colorAccent),
                ContextCompat.getColor(requireContext(), R.color.colorFavorite),
                ContextCompat.getColor(requireContext(), R.color.teal_200)
            )

            setOnRefreshListener {
                showSwipeRefreshLayout(false)
                setupListCategoryUI()
            }
        }
    }

    private fun setupListCategoryUI() {

        binding.listCategory.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = adapterCategories
        }


        binding.listRecipe.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mealsAdapter
        }

        viewModel.meals.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let { mealsResponse ->
                        mealsAdapter!!.differ.submitList(mealsResponse.meals.toList())
                        progressBar(false)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("error", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    progressBar(true)
                }

            }
        })

    }

    private fun getMeal(strCategory: String) {
        binding.textCategoryView.text = strCategory
        this.let {

            viewModel.getMealList(strCategory)

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapterCategories = null
        mealsAdapter = null
    }

    private fun progressBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showSwipeRefreshLayout(state: Boolean) {
        binding.refreshHome.isRefreshing = state
    }

    private val onItemClickedCategory = object : CategoryAdapter.OnItemClickCallback {
        override fun onItemClicked(strCategory: String) {
            getMeal(strCategory)
            progressBar(true)
            showSwipeRefreshLayout(false)

        }

    }


}