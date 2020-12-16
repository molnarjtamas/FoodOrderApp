package com.example.foodorderapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderapp.R
import com.example.foodorderapp.data.user.User
import com.example.foodorderapp.data.user.UserAdapter
import com.example.foodorderapp.data.user.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        //Recyclerview
        val adapter = UserAdapter()
        val recyclerView = view.recycler_view_profile


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //userViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllUsers.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        mUserViewModel.getCount.observe(viewLifecycleOwner, Observer { entries ->
            if(entries >0){
                floating_action_button_edit.isVisible = true
            }else{
                floating_action_button_add.isVisible = true
                view_create.isVisible = true
                text_view_create.isVisible = true
            }


        })



        view.floating_action_button_add.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addUserFragment)
        }


        return view
    }


}

