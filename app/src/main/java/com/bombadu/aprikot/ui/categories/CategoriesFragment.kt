package com.bombadu.aprikot.ui.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.databinding.FragmentCategoriesBinding
import com.bombadu.aprikot.local.CategoryEntity
import com.bombadu.aprikot.ui.recipes.RecipeListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {


    private val categoriesViewModel: CategoriesViewModel by lazy {
        ViewModelProvider(this).get(CategoriesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = FragmentCategoriesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = categoriesViewModel
        binding.categoryRecyclerView.adapter = CategoryAdapter(CategoryAdapter.OnClickListener {
            val intent = Intent(context, RecipeListActivity::class.java)
            val categoryItem = CategoryEntity(it.categoryId, it.categoryName,
                it.categoryDescription, it.categoryImageUrl)
            intent.putExtra(CATEGORY_ITEM, categoryItem)
            startActivity(intent)
        })

        return binding.root
    }

    companion object {
        const val CATEGORY_ITEM = "category_item"
    }

}