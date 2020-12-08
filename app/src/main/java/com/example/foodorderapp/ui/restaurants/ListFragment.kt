package com.example.foodorderapp.ui.restaurants


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by viewModels<RestaurantsViewModel>()

    private var _binding: FragmentListBinding?= null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentListBinding.bind(view)

        val adapter = RestaurantAdapter()

        binding.apply {
            recyclerViewRestaurants.setHasFixedSize(true)
            recyclerViewRestaurants.adapter= adapter
        }


        viewModel.restaurants.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}