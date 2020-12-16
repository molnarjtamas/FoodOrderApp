package com.example.foodorderapp.data.user

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodorderapp.R
import com.example.foodorderapp.ui.profile.ProfileFragmentDirections
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    private var userList = emptyList<User>()
    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.text_view_name.text = currentItem.name
        holder.itemView.text_view_email.text = currentItem.email
        holder.itemView.text_view_address.text= currentItem.address
        holder.itemView.text_view_phone.text= currentItem.phoneNumber

        val imageData = currentItem.image_byte_array
        val bmp = BitmapFactory.decodeByteArray(imageData,0,imageData.size)
        holder.itemView.image_view_avatar.setImageBitmap(bmp)

        holder.itemView.floating_action_button_edit.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToUpdateUserFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}