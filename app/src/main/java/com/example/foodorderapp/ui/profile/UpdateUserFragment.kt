package com.example.foodorderapp.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodorderapp.R
import com.example.foodorderapp.data.user.User
import com.example.foodorderapp.data.user.UserViewModel
import kotlinx.android.synthetic.main.fragment_update_user.*
import kotlinx.android.synthetic.main.fragment_update_user.view.*
import java.io.ByteArrayOutputStream


class UpdateUserFragment : Fragment() {

    private val args by navArgs<UpdateUserFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel
    lateinit var image_byte: ByteArray
    val REQUEST_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_user, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        view.edit_text_user_name_up.setText(args.currentUser.name)
        view.edit_text_user_address_up.setText(args.currentUser.address)
        view.edit_text_user_phone_number_up.setText(args.currentUser.phoneNumber)
        view.edit_text_user_email_up.setText(args.currentUser.email)

        image_byte = args.currentUser.image_byte_array
        val imageData = args.currentUser.image_byte_array
        val bmp = BitmapFactory.decodeByteArray(imageData,0,imageData.size)
        view.image_view_update_avatar.setImageBitmap(bmp)
       // image_byte = imageToBitmap(image_view_update_avatar)

        view.button_update_image.setOnClickListener {
            openGalleryForImage()
        }

        view.button_update_user.setOnClickListener {
            updateItem()
        }


        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            image_view_update_avatar.setImageURI(data?.data) // handle chosen image
            image_byte = imageToBitmap(image_view_update_avatar)

        }
    }

    private fun updateItem(){
        val name = edit_text_user_name_up.text.toString()
        val address = edit_text_user_address_up.text.toString()
        val phoneNumber = edit_text_user_phone_number_up.text.toString()
        val email = edit_text_user_email_up.text.toString()
        val img_byte = image_byte

        if(inputCheck(name, address, phoneNumber, email)){
            // Create User Object
            val updatedUser = User(args.currentUser.id, name, address, phoneNumber, email,img_byte)
            // Update Data to Database
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateUserFragment_to_profileFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }


    private fun inputCheck(name: String, address: String, phoneNumber: String, email: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(address) && TextUtils.isEmpty(
            phoneNumber
        ) && TextUtils.isEmpty(email)  )
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun imageToBitmap(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }
}