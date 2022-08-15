package com.android.recipeapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.recipeapp.R
import com.android.recipeapp.databinding.ListCategoryBinding
import com.android.recipeapp.model.Categories
import com.android.recipeapp.model.Meals
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val listCategory = ArrayList<Categories>()
    private var onItemClickCallback: OnItemClickCallback? = null


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val differCallback = object : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class CategoryViewHolder(private val binding: ListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categories: Categories) {


            binding.root.setOnClickListener {

                onItemClickCallback?.onItemClicked(categories.strCategory)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(categories.strCategoryThumb)
                    .apply(
                        RequestOptions.circleCropTransform()
                            .placeholder(R.drawable.beef)
                    )
                    .into(thumbnailCategory)

                textCategory.text = categories.strCategory


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder((view))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(strCategory: String)
    }
}