package com.bombadu.aprikot.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.favorite_recycler_view)
        setupRecyclerView()
        loadFavorites()

    }

    private fun loadFavorites() {
        favoritesViewModel.favorite.observe(viewLifecycleOwner, Observer { favData ->
            favData.let {
                favoriteAdapter.submitList(it)
                favoriteAdapter.notifyDataSetChanged()
            }
        })
        
    }

    private fun setupRecyclerView() = recyclerView.apply {
        favoriteAdapter = FavoriteAdapter()
        adapter = favoriteAdapter
        hasFixedSize()
        layoutManager = LinearLayoutManager(this.context)

    }
}