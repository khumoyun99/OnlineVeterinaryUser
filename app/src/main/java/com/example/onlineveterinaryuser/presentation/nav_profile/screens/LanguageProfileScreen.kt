package com.example.onlineveterinaryuser.presentation.nav_profile.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenLanguageProfileBinding
import com.example.onlineveterinaryuser.utils.scope

class LanguageProfileScreen:Fragment(R.layout.screen_language_profile) {

    private val binding by viewBinding(ScreenLanguageProfileBinding::bind)

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)


    }


}