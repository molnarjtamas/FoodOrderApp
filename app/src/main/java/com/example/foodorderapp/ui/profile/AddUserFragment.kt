package com.example.foodorderapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodorderapp.R
import com.example.foodorderapp.data.user.User
import com.example.foodorderapp.data.user.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_user.*
import kotlinx.android.synthetic.main.fragment_add_user.view.*

class AddUserFragment : Fragment(){

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.button_add_user.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = edit_text_user_name.text.toString()
        val address = edit_text_user_address.text.toString()
        val phoneNumber = edit_text_user_phone_number.text.toString()
        val email = edit_text_user_email.text.toString()


        if(inputCheck(name,address,phoneNumber,email)){
            // Create User Object
            val user = User(0,name,address,phoneNumber,email)
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addUserFragment_to_profileFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String,address:String,phoneNumber:String,email: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(address) && TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(email)  )
    }

}