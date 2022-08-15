package com.android.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.recipeapp.R
import com.android.recipeapp.databinding.ListRecipesBinding
import com.android.recipeapp.model.Meals
import com.android.recipeapp.ui.Search.SearchFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<Meals>() {
        override fun areItemsTheSame(oldItem: Meals, newItem: Meals): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meals, newItem: Meals): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class RecipesViewHolder(private val binding: ListRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meals: Meals) {
            binding.root.setOnClickListener {
                val direction = SearchFragmentDirections
                    .actionNavigationSearchToNavigationDetail(meals)
                it.findNavController().navigate(direction)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(meals.strMealThumb)
                    .apply(
                        RequestOptions.circleCropTransform()
                            .placeholder(R.drawable.ustsqw1468250014)
                    )
                    .into(thumbnailMeal)

                textMealName.text = meals.strMeal

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesAdapter.RecipesViewHolder {
        val view = ListRecipesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder((view))
    }

    override fun onBindViewHolder(holder: RecipesAdapter.RecipesViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}