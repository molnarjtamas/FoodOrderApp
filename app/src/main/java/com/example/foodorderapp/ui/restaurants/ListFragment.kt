package com.example.foodorderapp.ui.restaurants


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.foodorderapp.R
import com.example.foodorderapp.data.Restaurant
import com.example.foodorderapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), RestaurantAdapter.OnItemClickListener {

    //binding
    private val viewModel by viewModels<RestaurantsViewModel>()

    private var _binding: FragmentListBinding?= null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListBinding.bind(view)

        val adapter = RestaurantAdapter(this)


        binding.apply {
            recyclerViewRestaurants.setHasFixedSize(true)
            recyclerViewRestaurants.adapter= adapter
            recyclerViewRestaurants.adapter = adapter.withLoadStateHeaderAndFooter(
                header = RestaurantLoadStateAdapter { adapter.retry() },
                footer = RestaurantLoadStateAdapter { adapter.retry() },
            )
            buttonRetry.setOnClickListener{
                adapter.retry()
            }
        }


        viewModel.restaurants.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerViewRestaurants.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible= loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                if(loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1){
                    recyclerViewRestaurants.isVisible = false
                    textViewEmpty.isVisible =true
                }else{
                    textViewEmpty.isVisible = false
                }
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onItemClick(restaurant: Restaurant) {
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(restaurant)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_list, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(city: String?): Boolean {

                if (city != null) {
                    binding.recyclerViewRestaurants.scrollToPosition(0)
                    viewModel.searchRestaurants(city)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}