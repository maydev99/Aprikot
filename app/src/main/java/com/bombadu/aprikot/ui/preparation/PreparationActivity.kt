package com.bombadu.aprikot.ui.preparation

import android.app.NotificationManager
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityPreparationBinding
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.ui.recipes.RecipeListActivity
import com.bombadu.aprikot.util.sendNotification

class PreparationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreparationBinding
    private var isFavorite = false
    private var mMenu: Menu? = null
    private var id: Int = 0
    private lateinit var prepEntity: PreparationEntity


    private val preparationViewModel: PreparationViewModel by lazy {
        ViewModelProvider(this).get(PreparationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_preparation)

        val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val wakeOn = sharedPrefs.getBoolean("is_sleep_on", false)
        if (wakeOn) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)



        }

        binding.lifecycleOwner = this

        val application = requireNotNull(this).application

        val recipeItem = intent.extras!!.getParcelable<RecipeEntity>(RecipeListActivity.SELECTED_RECIPE)

        
        val viewModelFactory = PreparationViewModelFactory(recipeItem!!, application)

        binding.viewModel = viewModelFactory.let {
            ViewModelProvider(this,
                it
            ).get(PreparationViewModel::class.java)
        }

        preparationViewModel.preparations.observe(this, {
            prepEntity = it
            isFavorite = it.isFavorite
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.preparation_menu, menu)
        mMenu = menu

        if (isFavorite) {
            mMenu?.findItem(R.id.favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_red)
        } else {
            mMenu?.findItem(R.id.favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_outline_favorite_red_24)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite) {
                    mMenu?.findItem(R.id.favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_outline_favorite_red_24)
                    isFavorite = false
                    prepEntity.isFavorite = false
                    updateDB(prepEntity)
                } else {
                    mMenu?.findItem(R.id.favorite)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_red)
                    isFavorite = true
                    prepEntity.isFavorite = true
                    updateDB(prepEntity)
                }


            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateDB(preparationEntity: PreparationEntity) {
        preparationViewModel.insertUpdate(preparationEntity)
    }

}