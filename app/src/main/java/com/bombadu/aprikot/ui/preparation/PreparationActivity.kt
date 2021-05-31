package com.bombadu.aprikot.ui.preparation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityPreparationBinding
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.ui.recipes.RecipeListActivity
import com.squareup.picasso.Picasso

class PreparationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreparationBinding

    private val preparationViewModel: PreparationViewModel by lazy {
        ViewModelProvider(this).get(PreparationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_preparation)

        binding.lifecycleOwner = this

        val application = requireNotNull(this).application

        val recipeItem = intent.extras!!.getParcelable<RecipeEntity>(RecipeListActivity.SELECTED_RECIPE)

        //Log.i("TAG", "ID: ${recipeItem!!.recipeId}")

        val viewModelFactory = PreparationViewModelFactory(recipeItem!!, application)

        binding.viewModel = viewModelFactory.let {
            ViewModelProvider(this,
                it
            ).get(PreparationViewModel::class.java)
        }

        //preparationViewModel.getPreparationDataById(recipeItem)
     //preparationViewModel.refreshPreparationData(recipeItem.recipeId)

        //preparationViewModel.refreshPreparationData(recipeItem.recipeId)

        /*preparationViewModel.preparations.observe(this, Observer {
            Picasso.get().load(it.recipeImageUrl)
                .placeholder(R.drawable.placeholder_off_white)
                .error(R.drawable.placeholder_off_white)
                .into(binding.preparationImageView)

            binding.preparationTitleTextView.text = it.recipeName
        })*/

    }

}