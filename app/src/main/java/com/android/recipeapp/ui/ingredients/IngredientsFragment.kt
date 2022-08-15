package com.android.recipeapp.ui.ingredients

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.android.recipeapp.R
import com.android.recipeapp.databinding.FragmentIngredientsBinding
import com.android.recipeapp.ui.Detail.DetailFragment

class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!
    private lateinit var ingView1: TextView
    private lateinit var ingView2: TextView
    private lateinit var ingView3: TextView
    private lateinit var ingView4: TextView
    private lateinit var ingView5: TextView
    private lateinit var ingView6: TextView
    private lateinit var ingView7: TextView
    private lateinit var ingView8: TextView
    private lateinit var ingView9: TextView
    private lateinit var ingView10: TextView
    private lateinit var meaView1: TextView
    private lateinit var meaView2: TextView
    private lateinit var meaView3: TextView
    private lateinit var meaView4: TextView
    private lateinit var meaView5: TextView
    private lateinit var meaView6: TextView
    private lateinit var meaView7: TextView
    private lateinit var meaView8: TextView
    private lateinit var meaView9: TextView
    private lateinit var meaView10: TextView
    private lateinit var cardView10: CardView

    private lateinit var ingredient: String
    private lateinit var ingredient2: String
    private lateinit var ingredient3: String
    private lateinit var ingredient4: String
    private lateinit var ingredient5: String
    private lateinit var ingredient6: String
    private lateinit var ingredient7: String
    private lateinit var ingredient8: String
    private lateinit var ingredient9: String
    private var ingredient10: String? = ""
    private lateinit var measure1: String
    private lateinit var measure2: String
    private lateinit var measure3: String
    private lateinit var measure4: String
    private lateinit var measure5: String
    private lateinit var measure6: String
    private lateinit var measure7: String
    private lateinit var measure8: String
    private lateinit var measure9: String
    private lateinit var measure10: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        ingredient = args?.getString(DetailFragment.EXTRA_INGREDIENT_1).toString()
        ingredient2 = args?.getString(DetailFragment.EXTRA_INGREDIENT_2).toString()
        ingredient3 = args?.getString(DetailFragment.EXTRA_INGREDIENT_3).toString()
        ingredient4 = args?.getString(DetailFragment.EXTRA_INGREDIENT_4).toString()
        ingredient5 = args?.getString(DetailFragment.EXTRA_INGREDIENT_5).toString()
        ingredient6 = args?.getString(DetailFragment.EXTRA_INGREDIENT_6).toString()
        ingredient7 = args?.getString(DetailFragment.EXTRA_INGREDIENT_7).toString()
        ingredient8 = args?.getString(DetailFragment.EXTRA_INGREDIENT_8).toString()
        ingredient9 = args?.getString(DetailFragment.EXTRA_INGREDIENT_9).toString()
        ingredient10 = args?.getString(DetailFragment.EXTRA_INGREDIENT_10).toString()
        measure1 = args?.getString(DetailFragment.EXTRA_MEASURE_1).toString()
        measure2 = args?.getString(DetailFragment.EXTRA_MEASURE_2).toString()
        measure3 = args?.getString(DetailFragment.EXTRA_MEASURE_3).toString()
        measure4 = args?.getString(DetailFragment.EXTRA_MEASURE_4).toString()
        measure5 = args?.getString(DetailFragment.EXTRA_MEASURE_5).toString()
        measure6 = args?.getString(DetailFragment.EXTRA_MEASURE_6).toString()
        measure7 = args?.getString(DetailFragment.EXTRA_MEASURE_7).toString()
        measure8 = args?.getString(DetailFragment.EXTRA_MEASURE_8).toString()
        measure9 = args?.getString(DetailFragment.EXTRA_MEASURE_9).toString()
        measure10 = args?.getString(DetailFragment.EXTRA_MEASURE_10).toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI(view)

    }

    private fun setUI(view: View) {

        ingView1 = view.findViewById(R.id.ingredient1)
        ingView2 = view.findViewById(R.id.ingredient2)
        ingView3 = view.findViewById(R.id.ingredient3)
        ingView4 = view.findViewById(R.id.ingredient4)
        ingView5 = view.findViewById(R.id.ingredient5)
        ingView6 = view.findViewById(R.id.ingredient6)
        ingView7 = view.findViewById(R.id.ingredient7)
        ingView8 = view.findViewById(R.id.ingredient8)
        ingView9 = view.findViewById(R.id.ingredient9)
        ingView10 = view.findViewById(R.id.ingredient10)
        meaView1 = view.findViewById(R.id.measure1)
        meaView2 = view.findViewById(R.id.measure2)
        meaView3 = view.findViewById(R.id.measure3)
        meaView4 = view.findViewById(R.id.measure4)
        meaView5 = view.findViewById(R.id.measure5)
        meaView6 = view.findViewById(R.id.measure6)
        meaView7 = view.findViewById(R.id.measure7)
        meaView8 = view.findViewById(R.id.measure8)
        meaView9 = view.findViewById(R.id.measure9)
        meaView10 = view.findViewById(R.id.measure10)
        cardView10 = view.findViewById(R.id.cardIngredient10)


        ingView1.text = ingredient
        ingView2.text = ingredient2
        ingView3.text = ingredient3
        ingView4.text = ingredient4
        ingView5.text = ingredient5
        ingView6.text = ingredient6
        ingView7.text = ingredient7
        ingView8.text = ingredient8
        ingView9.text = ingredient9

        if (ingredient10 == "") {
            cardView10.visibility = View.GONE
        } else {
            ingView10.text = ingredient10
        }
        meaView1.text = measure1
        meaView2.text = measure2
        meaView3.text = measure3
        meaView4.text = measure4
        meaView5.text = measure5
        meaView6.text = measure6
        meaView7.text = measure7
        meaView8.text = measure8
        meaView9.text = measure9
        meaView10.text = measure10


    }


}