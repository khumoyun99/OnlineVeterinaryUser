package com.example.onlineveterinaryuser.presentation.nav_animals.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineveterinaryuser.R
import com.example.onlineveterinaryuser.databinding.ScreenEditAnimalsBinding
import com.example.onlineveterinaryuser.utils.scope

class EditAnimalsScreen:Fragment(R.layout.screen_edit_animals) {

    private val binding by viewBinding(ScreenEditAnimalsBinding::bind)

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) = binding.scope {
        super.onViewCreated(view , savedInstanceState)



    }

}