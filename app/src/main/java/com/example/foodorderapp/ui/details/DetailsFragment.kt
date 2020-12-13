package com.example.foodorderapp.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater

import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.util.*


class DetailsFragment : Fragment(R.layout.fragment_details) {

    //this is autogenerated, transmitting data from the previous view
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val restaurant = args.restaurant

            Glide.with(this@DetailsFragment)
                .load(restaurant.image_url)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewName.isVisible = true
                        textViewCity.isVisible = true
                        textViewState.isVisible = true
                        textViewCountry.isVisible = true
                        textViewAddress.isVisible = true
                        textViewPhoneNumber.isVisible = true

                        return false
                    }
                })
                .into(imageView)

            val rawPhoneNum = restaurant.phone
            val phoneNum= rawPhoneNum.filter{it.isDigit()}

            textViewName.text = restaurant.name
            textViewCity.text = restaurant.city
            textViewState.text = restaurant.state
            textViewCountry.text = restaurant.country
            textViewAddress.text = restaurant.address
            textViewPhoneNumber.text = phoneNum


            val gmmIntentUri: Uri =
                Uri.parse("google.navigation:q=${restaurant.lat},${restaurant.lng}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            
            imageViewLoc.apply {
                setOnClickListener {
                    context.startActivity(mapIntent)
                }
            }



            val callIntent= Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            imageViewPhone.apply {
                setOnClickListener {
                    context.startActivity(callIntent)
                }
            }

        }
    }



    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchItem.isVisible = false
        searchView.visibility = View.GONE
    }

}
