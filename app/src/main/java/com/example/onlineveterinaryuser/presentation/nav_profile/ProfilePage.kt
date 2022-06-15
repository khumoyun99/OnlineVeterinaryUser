package com.example.onlineveterinaryuser.presentation.nav_profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.PageProfileBinding
import com.example.onlineveterinaryuser.utils.scope

class ProfilePage:Fragment(R.layout.page_profile) {

    private val binding by viewBinding(PageProfileBinding::bind)

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu : Menu , inflater : MenuInflater) {
        super.onCreateOptionsMenu(menu , inflater)
        inflater.inflate(R.menu.profile_language_menu , menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId) {
            R.id.languageMenu -> {
                findNavController().navigate(ProfilePageDirections.actionProfilePageToLanguageProfileScreen())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}