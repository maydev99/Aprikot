package com.bombadu.aprikot.ui.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bombadu.aprikot.databinding.FragmentCategoriesBinding
import com.bombadu.aprikot.ui.recipes.RecipeListActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
            val category = it.categoryName
            val intent = Intent(context, RecipeListActivity::class.java)
            intent.putExtra("category_key", category)
            startActivity(intent)
        })




        return binding.root
    }


}