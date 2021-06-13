package com.bombadu.aprikot.ui.preparation

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.ActivityPreparationBinding
import com.bombadu.aprikot.local.PreparationEntity
import com.bombadu.aprikot.local.RecipeEntity
import com.bombadu.aprikot.network.ConnectionType
import com.bombadu.aprikot.network.NetworkMonitorUtil
import com.bombadu.aprikot.ui.MainActivity
import com.bombadu.aprikot.ui.recipes.RecipeListActivity


class PreparationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreparationBinding
    private var isFavorite = false
    private var mMenu: Menu? = null
    private lateinit var prepEntity: PreparationEntity
    private val networkMonitor = NetworkMonitorUtil(this)


    private val preparationViewModel: PreparationViewModel by lazy {
        ViewModelProvider(this).get(PreparationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_preparation)

        checkNetwork()

        val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val wakeOn = sharedPrefs.getBoolean("is_sleep_on", false)
        if (wakeOn) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        }

        binding.lifecycleOwner = this

        val application = requireNotNull(this).application

        val recipeItem =
            intent.extras!!.getParcelable<RecipeEntity>(RecipeListActivity.SELECTED_RECIPE)


        val viewModelFactory = PreparationViewModelFactory(recipeItem!!, application)

        binding.viewModel = viewModelFactory.let {
            ViewModelProvider(
                this,
                it
            ).get(PreparationViewModel::class.java)
        }

        preparationViewModel.preparations.observe(this, {
            try {
                prepEntity = it
                isFavorite = it.isFavorite



            } catch (e: Exception) {
                e.printStackTrace()
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.preparation_menu, menu)
        mMenu = menu

        if (isFavorite) {
            mMenu?.findItem(R.id.favorite)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_red)
        } else {
            mMenu?.findItem(R.id.favorite)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_outline_favorite_white_24)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite) {
                    mMenu?.findItem(R.id.favorite)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_outline_favorite_white_24)
                    isFavorite = false
                    prepEntity.isFavorite = false
                    updateDB(prepEntity)
                } else {
                    mMenu?.findItem(R.id.favorite)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_red)
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

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

    private fun checkNetwork() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when(isAvailable) {
                    true -> {
                        when(type) {
                            ConnectionType.Wifi -> {
                                Log.i(MainActivity.TAG, "Wifi Connected")
                            }

                            ConnectionType.Cellular -> {
                                Log.i(MainActivity.TAG, "Cellular Connected")
                            }

                            else -> {}
                        }
                    }
                    false -> {
                        Log.i(MainActivity.TAG, "No Connection")
                        Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}