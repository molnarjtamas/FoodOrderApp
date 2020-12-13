package com.example.foodorderapp.ui.profile

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.foodorderapp.R

class ProfileFragment: Fragment(R.layout.fragment_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchItem.isVisible = false
        searchView.visibility = View.GONE
    }
}