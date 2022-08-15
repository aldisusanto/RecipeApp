package com.android.recipeapp.ui.Detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.android.recipeapp.R
import com.android.recipeapp.databinding.FragmentDetailBinding
import com.android.recipeapp.databinding.FragmentFavoriteBinding
import com.android.recipeapp.model.Meals
import com.android.recipeapp.ui.FavoriteFragment
import com.android.submission2.ui.adapter.DetailInstructionsPageAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var recipe: Meals

    companion object {
        const val EXTRA_INGREDIENT_1 = "extra_ingredient_1"
        const val EXTRA_INGREDIENT_2 = "extra_ingredient_2"
        const val EXTRA_INGREDIENT_3 = "extra_ingredient_3"
        const val EXTRA_INGREDIENT_4 = "extra_ingredient_4"
        const val EXTRA_INGREDIENT_5 = "extra_ingredient_5"
        const val EXTRA_INGREDIENT_6 = "extra_ingredient_6"
        const val EXTRA_INGREDIENT_7 = "extra_ingredient_7"
        const val EXTRA_INGREDIENT_8 = "extra_ingredient_8"
        const val EXTRA_INGREDIENT_9 = "extra_ingredient_9"
        const val EXTRA_INGREDIENT_10 = "extra_ingredient_10"
        const val EXTRA_INGREDIENT_11 = "extra_ingredient_11"
        const val EXTRA_INGREDIENT_12 = "extra_ingredient_12"
        const val EXTRA_MEASURE_1 = "extra_measure_1"
        const val EXTRA_MEASURE_2 = "extra_measure_2"
        const val EXTRA_MEASURE_3 = "extra_measure_3"
        const val EXTRA_MEASURE_4 = "extra_measure_4"
        const val EXTRA_MEASURE_5 = "extra_measure_5"
        const val EXTRA_MEASURE_6 = "extra_measure_6"
        const val EXTRA_MEASURE_7 = "extra_measure_7"
        const val EXTRA_MEASURE_8 = "extra_measure_8"
        const val EXTRA_MEASURE_9 = "extra_measure_9"
        const val EXTRA_MEASURE_10 = "extra_measure_10"
        const val EXTRA_MEASURE_11 = "extra_measure_11"
        const val EXTRA_INSTRUCTION = "extra_instruction"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        recipe = args.recipe
        bundle.putString(EXTRA_INGREDIENT_1, recipe.strIngredient1)
        bundle.putString(EXTRA_INGREDIENT_2, recipe.strIngredient2)
        bundle.putString(EXTRA_INGREDIENT_3, recipe.strIngredient3)
        bundle.putString(EXTRA_INGREDIENT_4, recipe.strIngredient4)
        bundle.putString(EXTRA_INGREDIENT_5, recipe.strIngredient5)
        bundle.putString(EXTRA_INGREDIENT_6, recipe.strIngredient6)
        bundle.putString(EXTRA_INGREDIENT_7, recipe.strIngredient7)
        bundle.putString(EXTRA_INGREDIENT_8, recipe.strIngredient8)
        bundle.putString(EXTRA_INGREDIENT_9, recipe.strIngredient9)
        bundle.putString(EXTRA_INGREDIENT_10, recipe.strIngredient10)
        bundle.putString(EXTRA_MEASURE_1, recipe.strMeasure1)
        bundle.putString(EXTRA_MEASURE_2, recipe.strMeasure2)
        bundle.putString(EXTRA_MEASURE_3, recipe.strMeasure3)
        bundle.putString(EXTRA_MEASURE_4, recipe.strMeasure4)
        bundle.putString(EXTRA_MEASURE_5, recipe.strMeasure5)
        bundle.putString(EXTRA_MEASURE_6, recipe.strMeasure6)
        bundle.putString(EXTRA_MEASURE_7, recipe.strMeasure7)
        bundle.putString(EXTRA_MEASURE_8, recipe.strMeasure8)
        bundle.putString(EXTRA_MEASURE_9, recipe.strMeasure9)
        bundle.putString(EXTRA_MEASURE_10, recipe.strMeasure10)
        bundle.putString(EXTRA_MEASURE_11, recipe.strMeasure11)
        bundle.putString(EXTRA_INSTRUCTION, recipe.strInstructions)
        binding.apply {
            btnBack.setOnClickListener {
                it.findNavController().navigate(R.id.navigation_search)
            }
            textMealName.text = recipe.strMeal
            Glide.with(this@DetailFragment)
                .load(recipe.strMealThumb)
                .apply(
                    RequestOptions.circleCropTransform()
                        .placeholder(R.drawable.ustsqw1468250014)
                )
                .into(thumbnailMeal)


            val detailInstructionsPageAdapter =
                DetailInstructionsPageAdapter(this@DetailFragment, bundle)

            binding.apply {
                viewPagerMealDetail.adapter = detailInstructionsPageAdapter
                TabLayoutMediator(
                    binding.tabLayoutMealDetail,
                    binding.viewPagerMealDetail
                ) { tab, position ->
                    tab.text =
                        resources.getString(DetailInstructionsPageAdapter.TAB_TITLES[position])
                }.attach()
            }
            linkYoutube.setOnClickListener {
                val uri = Uri.parse(recipe.strYoutube)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

            var isChecked = false
            btnToggleFavorite.setOnClickListener {
                isChecked = !isChecked
                if (isChecked) {
                    feedbackFavorite(resources.getString(R.string.recipe_added_to_favorite), true)
                } else {
                    feedbackFavorite(
                        resources.getString(R.string.recipe_remove_from_favorite),
                        true
                    )
                }
            }

        }
    }

    private fun feedbackFavorite(message: String, useSnack: Boolean) {
        if (useSnack) {
            Snackbar.make(binding.fragmentDetail, message, Snackbar.LENGTH_LONG).apply {
                animationMode = Snackbar.ANIMATION_MODE_SLIDE
                setAction(resources.getString(R.string.favorite_action_label)) {
                    it.findNavController().navigate(R.id.navigation_favorite)
                }
                show()
            }
        } else {
            Toast.makeText(
                requireActivity(),
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}