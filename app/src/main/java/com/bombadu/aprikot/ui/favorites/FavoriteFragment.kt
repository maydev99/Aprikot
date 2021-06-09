package com.bombadu.aprikot.ui.favorites

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.aprikot.R
import com.bombadu.aprikot.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by lazy {
        ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var dialog: TextView
    private var isLandscape = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFavoriteBinding.inflate(inflater)


        dialog = binding.noFavoritesDialog
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        recyclerView = view.findViewById(R.id.favorite_recycler_view)
        setupRecyclerView()
        loadFavorites()

    }

    private fun loadFavorites() {
        favoritesViewModel.favorite.observe(viewLifecycleOwner,  { favData ->
            favData.let {
                if (it.isNullOrEmpty()) {
                    dialog.visibility = View.VISIBLE
                } else {
                    dialog.visibility = View.GONE
                }
                favoriteAdapter.submitList(it)
                favoriteAdapter.notifyDataSetChanged()
            }
        })
        
    }

    private fun setupRecyclerView() = recyclerView.apply {
        favoriteAdapter = FavoriteAdapter()
        adapter = favoriteAdapter
        hasFixedSize()
        layoutManager = if(isLandscape) {
            GridLayoutManager(this.context, 2)
        } else {
            LinearLayoutManager(this.context)
        }

    }
}