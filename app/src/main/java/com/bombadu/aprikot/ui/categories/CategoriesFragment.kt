package com.bombadu.aprikot.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.databinding.FragmentCategoriesBinding
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


       //
        // val categoriesViewModel = ViewModelProvider(requireActivity(), CategoriesViewModel::class.java)


        val binding = FragmentCategoriesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = categoriesViewModel




        binding.categoryTextView.setOnClickListener {
           /* categoriesViewModel.getCategoryData()

            categoriesViewModel.categoryData.observe(viewLifecycleOwner ) {
                binding.categoryTextView.text = it.toString()
            }*/
            Log.i("TAG", "CLick")

        }


        return binding.root
    }


}