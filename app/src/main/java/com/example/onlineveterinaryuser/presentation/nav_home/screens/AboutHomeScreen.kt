package com.example.onlineveterinaryuser.presentation.nav_home.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenAboutHomeBinding
import com.example.onlineveterinaryuser.utils.scope

class AboutHomeScreen:Fragment(R.layout.screen_about_home) {

    private val binding by viewBinding(ScreenAboutHomeBinding::bind)
    private val img:AboutHomeScreenArgs by navArgs()

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)

        imgInfoAnimalsImg.setBackgroundResource(img.animal.img)
    }

}